package kodlamaio.hrms.business.concretes;

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
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployerManager implements EmployerService {

    private EmployerDao employerDao;
    private UserDao userDao;
    private EmailService emailService;
    private VerificationCodeService verificationCodeService;

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
                checkIfEmailContainsWebSiteDomain(employer.getEmail(), employer.getWebAddress()),
                checkIfPasswordsMatch(employer.getPassword(), employer.getPasswordCheck())
        ));

        if(result.isSuccess()){
            DataResult<Employer> dataResult = new SuccessDataResult<>(
                    employerDao.save(employer),
                    "Employer saved successfully. Please verify your account."
            );
            VerificationCode verificationCode = addVerificationCode(employer);
            sendMail(employer.getEmail(),
                    "Please verify your email using code : " +
                            verificationCode.getCode());
            return dataResult;
        } else {
            return new ErrorDataResult<>(result.getMessage());
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

    private Result checkIfEmailContainsWebSiteDomain(String email, String website){
        if(!email.split("@")[1].contains(website)){
            return new ErrorResult(
                    "This email does not contain web site's domain."
            );
        } else {
            return new SuccessResult();
        }
    }

    private Result checkIfPasswordsMatch(String password, String passwordCheck){
        if(!password.equals(passwordCheck)) {
            return new ErrorResult(
                    "Passwords did not match."
            );
        } else {
            return new SuccessResult();
        }
    }
}
