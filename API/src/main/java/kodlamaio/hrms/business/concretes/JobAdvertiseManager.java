package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobAdvertiseService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.JobAdvertise;
import kodlamaio.hrms.repositories.JobAdvertiseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobAdvertiseManager implements JobAdvertiseService {

    private JobAdvertiseDao jobAdvertiseDao;

    @Autowired
    public JobAdvertiseManager(JobAdvertiseDao jobAdvertiseDao) {
        this.jobAdvertiseDao = jobAdvertiseDao;
    }

    @Override
    public DataResult<JobAdvertise> add(JobAdvertise jobAdvertise) {
        return null;
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrue() {
        return new SuccessDataResult(jobAdvertiseDao.findAllByActiveTrue(),
                "Data listed by active true");
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrueSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
        return new SuccessDataResult(jobAdvertiseDao.findAllByActiveTrue(sort),
                "Data listed by created date descending order.");
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrueAndEmployerUuid(UUID uuid) {
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao.findAllByActiveTrueAndEmployerUuid(uuid);
        if(jobAdvertises.isEmpty()){
            return new ErrorDataResult<>(jobAdvertises, "No Job Advertises found for given uuid.");
        } else {
            return new SuccessDataResult<>(jobAdvertises, "Data listed by employer");
        }
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrueAndCityId(Long id) {
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao.findAllByActiveTrueAndCityId(id);
        if(jobAdvertises.isEmpty()){
            return new ErrorDataResult<>(jobAdvertises, "No Job Advertises found for given city.");
        } else {
            return new SuccessDataResult<>(jobAdvertises, "Data listed by city");
        }
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrueAndJobPositionId(Long id) {
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao.findAllByActiveTrueAndJobPositionId(id);
        if(jobAdvertises.isEmpty()){
            return new ErrorDataResult<>(jobAdvertises, "No Job Advertises found for given job position.");
        } else {
            return new SuccessDataResult<>(jobAdvertises, "Data listed by job position");
        }
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(String companyName) {
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao
                .findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(companyName);
        if(jobAdvertises.isEmpty()){
            return new ErrorDataResult<>(jobAdvertises, "No Job Advertises found for given company name.");
        } else {
            return new SuccessDataResult<>(jobAdvertises, "Data listed by company name");
        }
    }
}
