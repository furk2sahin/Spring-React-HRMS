package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.BusinessRule;
import kodlamaio.hrms.business.abstracts.EmployerVerifyService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.EmployerVerify;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import kodlamaio.hrms.repositories.EmployerVerifyDao;
import kodlamaio.hrms.repositories.SystemPersonnelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class EmployerVerifyManager implements EmployerVerifyService {

    private final EmployerVerifyDao employerVerifyDao;
    private final SystemPersonnelDao systemPersonnelDao;

    @Autowired
    public EmployerVerifyManager(EmployerVerifyDao employerVerifyDao,
                                 SystemPersonnelDao systemPersonnelDao) {
        this.employerVerifyDao = employerVerifyDao;
        this.systemPersonnelDao = systemPersonnelDao;
    }

    @Override
    public DataResult<EmployerVerify> verifyEmployer(UUID employerUuid, UUID systemPersonnelUuid) {
        EmployerVerify employerVerify = employerVerifyDao.findByEmployerUuid(employerUuid).orElse(null);
        SystemPersonnel systemPersonnel = systemPersonnelDao.findByUuid(systemPersonnelUuid).orElse(null);

        Result result = ResultChecker.check(Arrays.asList(
                BusinessRule.checkIfEmployerVerifyExists(employerVerify),
                BusinessRule.checkIfSystemPersonnelExists(systemPersonnel),
                checkIfEmployerAlreadyVerified(employerUuid),
                BusinessRule.checkIfUuidValid(employerUuid, "Employer"),
                BusinessRule.checkIfUuidValid(systemPersonnelUuid, "System personnel")
        ));

        if(result.isSuccess()){
            employerVerify.setSystemPersonnel(systemPersonnel);
            employerVerify.setVerified(true);
            return new SuccessDataResult<>(
                    employerVerifyDao.save(employerVerify),
                    "Employer verified successfully."
            );
        } else {
            return new ErrorDataResult<>(result.getMessage());
        }
    }

    private Result checkIfEmployerAlreadyVerified(UUID uuid){
        if(employerVerifyDao.existsByEmployerUuidAndVerifiedTrue(uuid)){
            return new ErrorDataResult<>("This employer already verified.");
        } else {
            return new SuccessResult();
        }
    }
}