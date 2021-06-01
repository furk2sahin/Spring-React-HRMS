package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.adapter.abstracts.EmailService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.mapper.EmployerMapper;
import kodlamaio.hrms.model.concretes.VerificationCode;
import kodlamaio.hrms.model.dtos.concretes.EmployerGetDto;
import kodlamaio.hrms.model.dtos.concretes.EmployerPostDto;
import kodlamaio.hrms.repositories.EmployerDao;
import kodlamaio.hrms.model.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;
    private final BusinessRuleService businessRuleService;
    private final EmployerMapper employerMapper;

    @Autowired
    public EmployerManager(EmployerDao employerDao,
                           EmailService emailService,
                           VerificationCodeService verificationCodeService,
                           BusinessRuleService businessRuleService,
                           EmployerMapper employerMapper) {
        this.employerDao = employerDao;
        this.emailService = emailService;
        this.verificationCodeService = verificationCodeService;
        this.businessRuleService = businessRuleService;
        this.employerMapper = employerMapper;
    }

    @Override
    public DataResult<List<EmployerGetDto>> getAll() {
        return new SuccessDataResult<>(
                employerMapper.employersToEmployerGetDtos(employerDao.findAll()),
                "Data listed successfully");
    }

    @Override
    public ResponseEntity<DataResult<EmployerGetDto>> add(EmployerPostDto employerPostDto) {
        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfEmailExists(employerPostDto.getEmail()),
                businessRuleService.checkIfEmailContainsWebSiteDomain(employerPostDto.getEmail(), employerPostDto.getWebAddress()),
                businessRuleService.checkIfPasswordsMatch(employerPostDto.getPassword(), employerPostDto.getPasswordCheck())
        ));

        if(result.isSuccess()){
            Employer employer = employerMapper.employerPostDtoToEmployer(employerPostDto);
            DataResult<EmployerGetDto> dataResult = new SuccessDataResult<>(
                    employerMapper.employerToEmployerGetDto(employerDao.save(employer)),
                    "Employer saved successfully. Please verify your account."
            );
            VerificationCode verificationCode = addVerificationCode(employer);
            sendMail(employer.getEmail(),
                    "Please verify your email using code : http://localhost:8080/api/v1/verification-code/confirm/" +
                            dataResult.getData().getUuid() + "/"
                            +verificationCode.getCode()
            );
            return ResponseEntity.ok(dataResult);
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<EmployerGetDto>>> getAllPaged(int pageNo, int pageSize) {
        DataResult result = businessRuleService.checkIfPageNoAndPageSizeValid(pageNo, pageSize);
        if(result.isSuccess()){
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    employerMapper.employersToEmployerGetDtos(employerDao.findAll(pageable).getContent()),
                    "Data paged successfully. PageNo: " + (pageNo-1) + " PageSize: " + pageSize
            ));
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    private VerificationCode addVerificationCode(Employer employer){
        return verificationCodeService.add(employer);
    }

    private void sendMail(String email, String message){
        emailService.sendMail(email, message);
    }
}
