package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemPersonnelService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import kodlamaio.hrms.repositories.SystemPersonnelDao;
import kodlamaio.hrms.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SystemPersonnelManager implements SystemPersonnelService {

    private final SystemPersonnelDao systemPersonnelDao;
    private final UserDao userDao;

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

    @Override
    public DataResult<List<SystemPersonnel>> getAllPaged(int pageNo, int pageSize) {
        DataResult result = checkIfPageNoAndPageSizeValid(pageNo, pageSize);
        if(result.isSuccess()){
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            return new SuccessDataResult<>(systemPersonnelDao.findAll(pageable).getContent(),
                    "Data paged successfully. PageNo: " + (pageNo-1) + " PageSize: " + pageSize);
        } else {
            return result;
        }
    }

    private DataResult<Object> checkIfPageNoAndPageSizeValid(int pageNo, int pageSize){
        if(pageSize < 1){
            return new ErrorDataResult<>("Page size is not valid.");
        } else if(pageNo < 1){
            return new ErrorDataResult<>("Page number is not valid.");
        } else {
            return new SuccessDataResult<>();
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
