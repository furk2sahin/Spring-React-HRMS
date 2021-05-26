package kodlamaio.hrms.business.concretes;

import com.google.common.base.Strings;
import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.repositories.JobPositionDao;
import kodlamaio.hrms.model.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {

    private JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao){
        this.jobPositionDao = jobPositionDao;
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
                    "Successfull."
            );
        } catch (Exception e){
            return new ErrorDataResult<>("Error: " + e.getMessage());
        }
    }
}
