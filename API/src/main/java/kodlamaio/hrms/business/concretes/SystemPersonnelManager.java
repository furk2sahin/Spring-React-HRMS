package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SystemPersonnelService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.mapper.SystemPersonnelMapper;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import kodlamaio.hrms.model.dtos.concretes.SystemPersonnelGetDto;
import kodlamaio.hrms.model.dtos.concretes.SystemPersonnelPostDto;
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
    private final SystemPersonnelMapper systemPersonnelMapper;

    @Autowired
    public SystemPersonnelManager(SystemPersonnelDao systemPersonnelDao,
                                  BusinessRuleService businessRuleService,
                                  SystemPersonnelMapper systemPersonnelMapper) {
        this.systemPersonnelDao = systemPersonnelDao;
        this.businessRuleService = businessRuleService;
        this.systemPersonnelMapper = systemPersonnelMapper;
    }

    @Override
    public ResponseEntity<DataResult<SystemPersonnelGetDto>> add(SystemPersonnelPostDto systemPersonnelPostDto) {
        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfEmailExists(systemPersonnelPostDto.getEmail()),
                businessRuleService.checkIfPasswordsMatch(
                        systemPersonnelPostDto.getPassword(),
                        systemPersonnelPostDto.getPasswordCheck()
                )
        ));

        if(result.isSuccess()){
            SystemPersonnel systemPersonnel = systemPersonnelMapper.map(systemPersonnelPostDto);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    systemPersonnelMapper.map(systemPersonnelDao.save(systemPersonnel)),
                    "System personnel saved successfully."
            ));
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<SystemPersonnelGetDto>>> getAllPaged(int pageNo, int pageSize) {
        DataResult result = businessRuleService.checkIfPageNoAndPageSizeValid(pageNo, pageSize);
        if(result.isSuccess()){
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    systemPersonnelMapper.map(systemPersonnelDao.findAll(pageable).getContent()),
                    "Data paged successfully. PageNo: " + (pageNo-1) + " PageSize: " + pageSize
            ));
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        }
    }
}
