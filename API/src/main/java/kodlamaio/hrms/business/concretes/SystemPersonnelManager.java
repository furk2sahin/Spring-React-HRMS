package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemPersonnelService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import kodlamaio.hrms.repositories.SystemPersonnelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SystemPersonnelManager implements SystemPersonnelService {

    private final SystemPersonnelDao systemPersonnelDao;
    private final BusinessRuleService businessRuleService;

    @Autowired
    public SystemPersonnelManager(SystemPersonnelDao systemPersonnelDao,
                                  BusinessRuleService businessRuleService) {
        this.systemPersonnelDao = systemPersonnelDao;
        this.businessRuleService = businessRuleService;
    }

    @Override
    public ResponseEntity<DataResult<SystemPersonnel>> add(SystemPersonnel systemPersonnel) {
        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfEmailExists(systemPersonnel.getEmail()),
                businessRuleService.checkIfPasswordsMatch(systemPersonnel.getPassword(), systemPersonnel.getPasswordCheck())
        ));
        if(result.isSuccess()){
            return ResponseEntity.ok(new SuccessDataResult<>(
                    systemPersonnelDao.save(systemPersonnel),
                    "System personnel saved successfully."
            ));
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<SystemPersonnel>>> getAllPaged(int pageNo, int pageSize) {
        DataResult result = businessRuleService.checkIfPageNoAndPageSizeValid(pageNo, pageSize);
        if(result.isSuccess()){
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            return ResponseEntity.ok(new SuccessDataResult<>(systemPersonnelDao.findAll(pageable).getContent(),
                    "Data paged successfully. PageNo: " + (pageNo-1) + " PageSize: " + pageSize
            ));
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        }
    }
}
