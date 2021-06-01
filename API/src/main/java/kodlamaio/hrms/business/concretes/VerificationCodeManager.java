package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployerVerifyService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.codegenerator.CodeGenerator;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.model.concretes.Employer;
import kodlamaio.hrms.model.concretes.VerificationCode;
import kodlamaio.hrms.repositories.EmployerDao;
import kodlamaio.hrms.repositories.VerificationCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerificationCodeManager implements VerificationCodeService {

    private final VerificationCodeDao verificationCodeDao;
    private final EmployerDao employerDao;
    private final EmployerVerifyService employerVerifyService;

    @Autowired
    public VerificationCodeManager(VerificationCodeDao verificationCodeDao,
                                   EmployerDao employerDao,
                                   EmployerVerifyService employerVerifyService) {
        this.verificationCodeDao = verificationCodeDao;
        this.employerDao = employerDao;
        this.employerVerifyService = employerVerifyService;
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
    public ResponseEntity<Result> confirm(VerificationCode code, String verificationCode) {
        if(code.getCode().equals(verificationCode)){
            code.setConfirmed(true);
            verificationCodeDao.save(code);
            Employer employer = employerDao.findByUuid(code.getUser().getUuid()).orElse(null);
            employerVerifyService.add(employer);
            return ResponseEntity.ok(new SuccessResult("Thank you for confirming your account! " +
                        "Please contact system personnel to verify your account!"
            ));
        } else{
            return ResponseEntity.badRequest().body(new ErrorResult("Your verification code did not match."));
        }
    }

    @Override
    public ResponseEntity<DataResult<VerificationCode>> findByUserUuid(UUID uuid) {
        VerificationCode code = verificationCodeDao.findByUserUuid(uuid)
                .orElse(null);
        if(code == null){
            return ResponseEntity.badRequest().body(
                    new ErrorDataResult<>("No verification code found with given user uuid."
            ));
        } else {
            return ResponseEntity.ok(new SuccessDataResult<>(code, "Code found!"));
        }
    }
}
