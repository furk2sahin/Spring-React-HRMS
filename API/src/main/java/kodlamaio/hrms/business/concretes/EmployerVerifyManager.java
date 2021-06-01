package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployerVerifyService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.Employer;
import kodlamaio.hrms.model.concretes.EmployerVerify;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import kodlamaio.hrms.repositories.EmployerVerifyDao;
import kodlamaio.hrms.repositories.SystemPersonnelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class EmployerVerifyManager implements EmployerVerifyService {

    private final EmployerVerifyDao employerVerifyDao;
    private final SystemPersonnelDao systemPersonnelDao;
    private final BusinessRuleService businessRuleService;

    @Autowired
    public EmployerVerifyManager(EmployerVerifyDao employerVerifyDao,
                                 SystemPersonnelDao systemPersonnelDao,
                                 BusinessRuleService businessRuleService) {
        this.employerVerifyDao = employerVerifyDao;
        this.systemPersonnelDao = systemPersonnelDao;
        this.businessRuleService = businessRuleService;
    }

    @Override
    public DataResult<EmployerVerify> add(Employer employer) {
        EmployerVerify employerVerify = new EmployerVerify();
        employerVerify.setEmployer(employer);
        employerVerify.setVerified(false);
        return new SuccessDataResult<>(employerVerifyDao.save(employerVerify));
    }

    @Override
    public ResponseEntity<DataResult<EmployerVerify>> verifyEmployer(UUID employerUuid, UUID systemPersonnelUuid) {
        EmployerVerify employerVerify = employerVerifyDao.findByEmployerUuid(employerUuid).orElse(null);
        SystemPersonnel systemPersonnel = systemPersonnelDao.findByUuid(systemPersonnelUuid).orElse(null);

        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfEmployerVerifyExists(employerVerify),
                businessRuleService.checkIfSystemPersonnelExists(systemPersonnel),
                checkIfEmployerAlreadyVerified(employerUuid),
                businessRuleService.checkIfUuidValid(employerUuid, "Employer"),
                businessRuleService.checkIfUuidValid(systemPersonnelUuid, "System personnel")
        ));

        if(result.isSuccess()){
            employerVerify.setSystemPersonnel(systemPersonnel);
            employerVerify.setVerified(true);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    employerVerifyDao.save(employerVerify),
                    "Employer verified successfully."
            ));
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
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