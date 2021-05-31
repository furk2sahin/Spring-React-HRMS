package kodlamaio.hrms.business.concretes;

import com.google.common.base.Strings;
import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.repositories.JobPositionDao;
import kodlamaio.hrms.model.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {

    private final JobPositionDao jobPositionDao;
    private final BusinessRuleService businessRuleService;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao,
                              BusinessRuleService businessRuleService){
        this.jobPositionDao = jobPositionDao;
        this.businessRuleService = businessRuleService;
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<>(jobPositionDao.findAll(), "Data listed successfully");
    }

    @Override
    public DataResult<JobPosition> add(JobPosition jobPosition) {
        if(jobPositionDao.existsByJobName(jobPosition.getJobName())){
            return new ErrorDataResult<>("This job already exist.");
        }
        return new SuccessDataResult<>(
                jobPositionDao.save(jobPosition),
                "Data saved successfully."
        );
    }

    @Override
    public DataResult<JobPosition> getByName(String name) {
        if(Strings.isNullOrEmpty(name)){
            return new ErrorDataResult<>("Parameter cannot be empty.");
        }
        try{
            return new SuccessDataResult<>(
                    jobPositionDao.getByJobName(name),
                    "Successful."
            );
        } catch (Exception e){
            return new ErrorDataResult<>("Error: " + e.getMessage());
        }
    }

    @Override
    public DataResult<List<JobPosition>> getAllPaged(int pageNo, int pageSize) {
        DataResult result = businessRuleService.checkIfPageNoAndPageSizeValid(pageNo, pageSize);
        if(result.isSuccess()){
            Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
            return new SuccessDataResult<>(jobPositionDao.findAll(pageable).getContent(),
                    "Data paged successfully. PageNo: " + (pageNo-1) + " PageSize: " + pageSize);
        } else {
            return result;
        }
    }
}
