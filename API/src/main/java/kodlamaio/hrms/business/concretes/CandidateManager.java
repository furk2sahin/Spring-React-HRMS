package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.rules.BusinessRuleManager;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.adapter.abstracts.EmailService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.VerificationCode;
import kodlamaio.hrms.repositories.CandidateDao;
import kodlamaio.hrms.model.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CandidateManager implements CandidateService {

    private final CandidateDao candidateDao;
    private final BusinessRuleService businessRuleService;
    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    public CandidateManager(CandidateDao candidateDao,
                            BusinessRuleService businessRuleService,
                            EmailService emailService,
                            VerificationCodeService verificationCodeService) {
        this.candidateDao = candidateDao;
        this.businessRuleService= businessRuleService;
        this.emailService = emailService;
        this.verificationCodeService = verificationCodeService;
    }

    @Override
    public DataResult<List<Candidate>> getAll() {
        return new SuccessDataResult<>(candidateDao.findAll(), "Data listed successfully");
    }

    @Override
    public DataResult<Candidate> add(Candidate candidate) {
        if(candidate.getBirthDate() == null){
            return new ErrorDataResult<>(
                    "BirthDate cannot be null."
            );
        }
        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfEmailExists(candidate.getEmail()),
                checkIfNationalIdentityExists(candidate.getNationalIdentity()),
                businessRuleService.checkIfUserInformationCorrect(
                        candidate.getNationalIdentity(),
                        candidate.getName(),
                        candidate.getSurname(),
                        candidate.getBirthDate().toLocalDate().getYear()),
                businessRuleService.checkIfPasswordsMatch(candidate.getPassword(), candidate.getPasswordCheck())
        ));

        if(result.isSuccess()){
            DataResult<Candidate> dataResult = new SuccessDataResult<>(
                    candidateDao.save(candidate),
                    "Candidate saved successfully."
            );
            VerificationCode verificationCode = addVerificationCode(candidate);
            sendMail(candidate.getEmail(),
                    "Please verify your email using code : http://localhost:8080/api/v1/verification-code/verify-candidate/" +
                            dataResult.getData().getUuid() + "/"
                            +verificationCode.getCode()
            );
            return dataResult;
        } else {
            return new ErrorDataResult<>(result.getMessage());
        }
    }

    @Override
    public DataResult<List<Candidate>> getAllPaged(int pageNo, int pageSize) {
        DataResult result = businessRuleService.checkIfPageNoAndPageSizeValid(pageNo, pageSize);
        if(result.isSuccess()){
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            return new SuccessDataResult<>(candidateDao.findAll(pageable).getContent(),
                    "Data paged successfully. PageNo: " + (pageNo-1) + " PageSize: " + pageSize);
        } else {
            return result;
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
}
