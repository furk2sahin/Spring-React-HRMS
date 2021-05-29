package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.JobAdvertiseService;
import kodlamaio.hrms.core.enums.JobAdvertiseSort;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.JobAdvertise;
import kodlamaio.hrms.repositories.JobAdvertiseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class JobAdvertiseManager implements JobAdvertiseService {

    private JobAdvertiseDao jobAdvertiseDao;

    @Autowired
    public JobAdvertiseManager(JobAdvertiseDao jobAdvertiseDao) {
        this.jobAdvertiseDao = jobAdvertiseDao;
    }

    @Override
    public DataResult<JobAdvertise> add(JobAdvertise jobAdvertise, int expiryInDays) {
        Result result = ResultChecker.check(Arrays.asList(
                checkIfCityIdValid(jobAdvertise.getCity().getId()),
                checkIfJobIdValid(jobAdvertise.getJobPosition().getId()),
                checkIfEmployerIdValid(jobAdvertise.getEmployer().getId()),
                checkIfSalariesValid(jobAdvertise.getMaxSalary(), jobAdvertise.getMinSalary()),
                checkIfOpenPositionValid(jobAdvertise.getOpenPositionCount()),
                checkIfExpiryDayValid(expiryInDays)
        ));

        if(result.isSuccess()){
            jobAdvertise.setExpirationDate(LocalDate.now().plusDays(expiryInDays));
            return new SuccessDataResult<>(
                    jobAdvertiseDao.save(jobAdvertise),
                    "Job Advertise saved successfully."
            );
        } else {
            return new ErrorDataResult<>(result.getMessage());
        }
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrue() {
        return new SuccessDataResult<>(jobAdvertiseDao.findAllByActiveTrue(),
                "Data listed by active true");
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrueSorted(int sortId) {
        if(sortId < 1 || sortId > 8){
            return new ErrorDataResult<>("Sort Id should be between 1 and 8");
        }
        JobAdvertiseSort[] jobAdvertiseSorts = JobAdvertiseSort.values(); // return JobAdvertiseSort enum array
        JobAdvertiseSort jobAdvertiseSort = jobAdvertiseSorts[sortId-1]; // take one that equals sortId
        Sort sort = Sort.by(jobAdvertiseSort.getDirection(), jobAdvertiseSort.getProperty()); // Create sort with enum

        return new SuccessDataResult<>(
                jobAdvertiseDao.findAllByActiveTrue(sort),
                "Data listed " + jobAdvertiseSort.name()
        );
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrueAndEmployerUuid(UUID uuid) {
        String regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(regexp);
        if(!pattern.matcher(uuid.toString()).matches()){
            return new ErrorDataResult<>("UUID not valid.");
        }
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao.findAllByActiveTrueAndEmployerUuid(uuid);
        if(jobAdvertises.isEmpty()){
            return new ErrorDataResult<>(jobAdvertises, "No Job Advertises found for given uuid.");
        } else {
            return new SuccessDataResult<>(jobAdvertises, "Data listed by employer");
        }
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrueAndCityId(Long id) {
        DataResult result = checkIfIdValid(id);
        if(!result.isSuccess()){
            return result;
        }
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao.findAllByActiveTrueAndCityId(id);
        if(jobAdvertises.isEmpty()){
            return new ErrorDataResult<>(jobAdvertises, "No Job Advertises found for given city.");
        } else {
            return new SuccessDataResult<>(jobAdvertises, "Data listed by city");
        }
    }

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTrueAndJobPositionId(Long id) {
        DataResult result = checkIfIdValid(id);
        if(!result.isSuccess()){
            return result;
        }
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

    @Override
    public DataResult<List<JobAdvertise>> findAllByActiveTruePaged(int pageNumber, int pageSize) {
        DataResult dataResult = checkIfPageNoAndPageSizeValid(pageNumber, pageSize);
        if(dataResult.isSuccess()){
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            return new SuccessDataResult<>(
                    jobAdvertiseDao.findAll(pageable).getContent(),
                    "Data listed successfully. Page no:" + pageNumber +  " and Page size: " + pageSize
            );
        } else {
            return dataResult;
        }
    }

    @Override
    public DataResult<JobAdvertise> update(Long id, boolean active) {
        JobAdvertise jobAdvertise = jobAdvertiseDao.findById(id).orElse(null);
        if(jobAdvertise == null){
            return new ErrorDataResult<>("No job advertise found with given id");
        }
        if(jobAdvertise.isActive() == active){
            return new ErrorDataResult<>("Already " + active);
        } else {
            return new SuccessDataResult<>("Job status successfully turned to " + active);
        }
    }


    private DataResult<Object> checkIfIdValid(Long id){
        if(id < 1){
            return new ErrorDataResult<>("Id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    private DataResult<Object> checkIfCityIdValid(Long id){
        if(id < 1 || id > 81){
            return new ErrorDataResult<>("City id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    private DataResult<Object> checkIfJobIdValid(Long id){
        if(id < 1){
            return new ErrorDataResult<>("Job id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    private DataResult<Object> checkIfEmployerIdValid(Long id){
        if(id < 1){
            return new ErrorDataResult<>("Employer id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    private DataResult<Object> checkIfSalariesValid(int maxSalary, int minSalary){
        if(maxSalary < 1){
            return new ErrorDataResult<>("max salary is not valid.");
        } else if(minSalary < 1){
            return new ErrorDataResult<>("min salary is not valid.");
        } else if(maxSalary < minSalary){
            return new ErrorDataResult<>("min salary cannot be greater than max salary.");
        } else{
            return new SuccessDataResult<>();
        }
    }

    private DataResult<Object> checkIfOpenPositionValid(int id){
        if(id < 1){
            return new ErrorDataResult<>("Job id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    private DataResult<Object> checkIfExpiryDayValid(int id){
        if(id < 1){
            return new ErrorDataResult<>("Expiry Day is not valid.");
        } else {
            return new SuccessDataResult<>();
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
}