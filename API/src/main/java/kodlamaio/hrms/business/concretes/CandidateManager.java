package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.adapter.abstracts.EmailService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.mapper.CandidateMapper;
import kodlamaio.hrms.model.concretes.VerificationCode;
import kodlamaio.hrms.model.dtos.concretes.CandidateGetDto;
import kodlamaio.hrms.model.dtos.concretes.CandidatePostDto;
import kodlamaio.hrms.repositories.CandidateDao;
import kodlamaio.hrms.model.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CandidateManager implements CandidateService {

    private final CandidateDao candidateDao;
    private final BusinessRuleService businessRuleService;
    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;
    private final CandidateMapper candidateMapper;

    @Autowired
    public CandidateManager(CandidateDao candidateDao,
                            BusinessRuleService businessRuleService,
                            EmailService emailService,
                            VerificationCodeService verificationCodeService,
                            CandidateMapper candidateMapper) {
        this.candidateDao = candidateDao;
        this.businessRuleService= businessRuleService;
        this.emailService = emailService;
        this.verificationCodeService = verificationCodeService;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public DataResult<List<CandidateGetDto>> getAll() {
        return new SuccessDataResult<>(
                candidateMapper.candidatesToCandidateGetDtos(candidateDao.findAll()),
                "Data listed successfully");
    }

    @Override
    public ResponseEntity<DataResult<CandidateGetDto>> add(CandidatePostDto candidatePostDto) {
        if(candidatePostDto.getBirthDate() == null){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(
                    "BirthDate cannot be null."
            ));
        }
        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfEmailExists(candidatePostDto.getEmail()),
                checkIfNationalIdentityExists(candidatePostDto.getNationalIdentity()),
                businessRuleService.checkIfUserInformationCorrect(
                        candidatePostDto.getNationalIdentity(),
                        candidatePostDto.getName(),
                        candidatePostDto.getSurname(),
                        candidatePostDto.getBirthDate().toLocalDate().getYear()),
                businessRuleService.checkIfPasswordsMatch(candidatePostDto.getPassword(),
                        candidatePostDto.getPasswordCheck())
        ));

        if(result.isSuccess()){
            Candidate candidate = candidateMapper.candidatePostDtoToCandidate(candidatePostDto);
            candidateDao.save(candidate);
            DataResult<CandidateGetDto> dataResult = new SuccessDataResult<>(
                    candidateMapper.candidateToCandidateGetDto(candidate),
                    "Candidate saved successfully."
            );
            VerificationCode verificationCode = addVerificationCode(candidate);
            sendMail(candidate.getEmail(),
                    "Please verify your email using code : " +
                            "http://localhost:8080/api/v1/verification-code/verify-candidate/" +
                            dataResult.getData().getUuid() + "/"
                            +verificationCode.getCode()
            );
            return ResponseEntity.ok(dataResult);
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateGetDto>>> getAllPaged(int pageNo, int pageSize) {
        DataResult result = businessRuleService.checkIfPageNoAndPageSizeValid(pageNo, pageSize);
        if(result.isSuccess()){
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    candidateMapper.candidatesToCandidateGetDtos(candidateDao.findAll(pageable).getContent()),
                    "Data paged successfully. PageNo: " + (pageNo-1) + " PageSize: " + pageSize));
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    private VerificationCode addVerificationCode(Candidate candidate){
        return verificationCodeService.add(candidate);
    }

    private void sendMail(String email, String message){
        emailService.sendMail(email, message);
    }

    private Result checkIfNationalIdentityExists(String nationalIdentity){
        if(candidateDao.existsByNationalIdentity(nationalIdentity)){
            return new ErrorResult(
                    "An user exists with this national identity."
            );
        } else {
            return new SuccessResult();
        }
    }

    @Override
    public boolean existsById(Long id) {
        return candidateDao.existsById(id);
    }
}
