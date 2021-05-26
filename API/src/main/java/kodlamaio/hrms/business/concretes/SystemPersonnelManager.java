package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemPersonnelService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.Candidate;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import kodlamaio.hrms.model.concretes.VerificationCode;
import kodlamaio.hrms.repositories.SystemPersonnelDao;
import kodlamaio.hrms.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SystemPersonnelManager implements SystemPersonnelService {

    private SystemPersonnelDao systemPersonnelDao;
    private UserDao userDao;

    @Autowired
    public SystemPersonnelManager(SystemPersonnelDao systemPersonnelDao, UserDao userDao) {
        this.systemPersonnelDao = systemPersonnelDao;
        this.userDao = userDao;
    }

    @Override
    public DataResult<SystemPersonnel> add(SystemPersonnel systemPersonnel) {
        Result result = ResultChecker.check(Arrays.asList(
                checkIfEmailExists(systemPersonnel.getEmail()),
                checkIfPasswordsMatch(systemPersonnel.getPassword(), systemPersonnel.getPasswordCheck())
        ));
        if(result.isSuccess()){
            return new SuccessDataResult<>(
                    systemPersonnelDao.save(systemPersonnel),
                    "System personnel saved successfully."
            );
        } else {
            return new ErrorDataResult<>(result.getMessage());
        }
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
