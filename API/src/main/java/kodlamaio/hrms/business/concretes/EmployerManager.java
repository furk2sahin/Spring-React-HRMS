package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.BusinessRule;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.adapter.abstracts.EmailService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.VerificationCode;
import kodlamaio.hrms.repositories.EmployerDao;
import kodlamaio.hrms.model.concretes.Employer;
import kodlamaio.hrms.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private final EmployerDao employerDao;
    private final UserDao userDao;
    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    public EmployerManager(EmployerDao employerDao,
                           UserDao userDao,
                           EmailService emailService,
                           VerificationCodeService verificationCodeService) {
        this.employerDao = employerDao;
        this.userDao = userDao;
        this.emailService = emailService;
        this.verificationCodeService = verificationCodeService;
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<>(employerDao.findAll(), "Data listed successfully");
    }

    @Override
    public DataResult<Employer> add(Employer employer) {
        Result result = ResultChecker.check(Arrays.asList(
                checkIfEmailExists(employer.getEmail()),
                BusinessRule.checkIfEmailContainsWebSiteDomain(employer.getEmail(), employer.getWebAddress()),
                BusinessRule.checkIfPasswordsMatch(employer.getPassword(), employer.getPasswordCheck())
        ));

        if(result.isSuccess()){
            DataResult<Employer> dataResult = new SuccessDataResult<>(
                    employerDao.save(employer),
                    "Employer saved successfully. Please verify your account."
            );
            VerificationCode verificationCode = addVerificationCode(employer);
            sendMail(employer.getEmail(),
                    "Please verify your email using code : http://localhost:8080/api/v1/verification-code/verify/" +
                            dataResult.getData().getUuid() + "/"
                            +verificationCode.getCode()
            );
            return dataResult;
        } else {
            return new ErrorDataResult<>(result.getMessage());
        }
    }

    @Override
    public DataResult<List<Employer>> getAllPaged(int pageNo, int pageSize) {
        DataResult result = BusinessRule.checkIfPageNoAndPageSizeValid(pageNo, pageSize);
        if(result.isSuccess()){
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            return new SuccessDataResult<>(employerDao.findAll(pageable).getContent(),
                    "Data paged successfully. PageNo: " + (pageNo-1) + " PageSize: " + pageSize);
        } else {
            return result;
        }
    }

    private VerificationCode addVerificationCode(Employer employer){
        return verificationCodeService.add(employer);
    }

    private void sendMail(String email, String message){
        emailService.sendMail(email, message);
    }

    private Result checkIfEmailExists(String email){
        if(userDao.existsByEmail(email)){
            return new ErrorResult(
                    "This email already taken."
            );
        } else {
            return new SuccessResult();
        }
    }
}
