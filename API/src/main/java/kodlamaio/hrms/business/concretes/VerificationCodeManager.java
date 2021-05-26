package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.codegenerator.CodeGenerator;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.abstracts.User;
import kodlamaio.hrms.model.concretes.Employer;
import kodlamaio.hrms.model.concretes.EmployerVerify;
import kodlamaio.hrms.model.concretes.VerificationCode;
import kodlamaio.hrms.repositories.EmployerDao;
import kodlamaio.hrms.repositories.EmployerVerifyDao;
import kodlamaio.hrms.repositories.VerificationCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerificationCodeManager implements VerificationCodeService {

    private VerificationCodeDao verificationCodeDao;
    private EmployerDao employerDao;
    private EmployerVerifyDao employerVerifyDao;

    @Autowired
    public VerificationCodeManager(VerificationCodeDao verificationCodeDao,
                                   EmployerDao employerDao,
                                   EmployerVerifyDao employerVerifyDao) {
        this.verificationCodeDao = verificationCodeDao;
        this.employerDao = employerDao;
        this.employerVerifyDao = employerVerifyDao;
    }

    @Override
    public VerificationCode add(User user) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(CodeGenerator.generateCode());
        verificationCode.setConfirmed(false);
        verificationCode.setUser(user);
        return verificationCodeDao.save(verificationCode);
    }

    @Override
    public Result update(VerificationCode code, String verificationCode) {
        if(code.getCode().equals(verificationCode)){
            code.setConfirmed(true);
            verificationCodeDao.save(code);
            Employer employer = employerDao.findByUuid(code.getUser().getUuid()).orElse(null);
            if(employer == null){
                return new SuccessResult("Thank you for confirming your account! ");
            } else{
                EmployerVerify employerVerify = new EmployerVerify();
                employerVerify.setEmployer(employer);
                employerVerify.setVerified(false);
                employerVerifyDao.save(employerVerify);
                return new SuccessResult("Thank you for confirming your account! " +
                        "Please contact system personnel to verify your account!");
            }
        } else{
            return new ErrorResult("Your verification code did not match.");
        }
    }

    @Override
    public DataResult<VerificationCode> findByUserUuid(UUID uuid) {
        VerificationCode code = verificationCodeDao.findByUserUuid(uuid)
                .orElse(null);
        if(code == null){
            return new ErrorDataResult<>("No verification code found with given user uuid.");
        } else {
            return new SuccessDataResult<>(code, "Code found!");
        }
    }

}
