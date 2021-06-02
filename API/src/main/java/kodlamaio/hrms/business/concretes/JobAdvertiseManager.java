package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobAdvertiseService;
import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.enums.JobAdvertiseSort;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.mapper.JobAdvertiseMapper;
import kodlamaio.hrms.model.concretes.JobAdvertise;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertiseGetDto;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertisePostDto;
import kodlamaio.hrms.repositories.JobAdvertiseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class JobAdvertiseManager implements JobAdvertiseService {

    private final JobAdvertiseDao jobAdvertiseDao;
    private final BusinessRuleService businessRuleService;
    private final JobAdvertiseMapper jobAdvertiseMapper;
    private final JobPositionService jobPositionService;
    private final EmployerService employerService;

    @Autowired
    public JobAdvertiseManager(JobAdvertiseDao jobAdvertiseDao,
                               BusinessRuleService businessRuleService,
                               JobAdvertiseMapper jobAdvertiseMapper,
                               JobPositionService jobPositionService,
                               EmployerService employerService) {
        this.jobAdvertiseDao = jobAdvertiseDao;
        this.businessRuleService = businessRuleService;
        this.jobAdvertiseMapper = jobAdvertiseMapper;
        this.jobPositionService = jobPositionService;
        this.employerService = employerService;
    }

    @Override
    public DataResult<JobAdvertiseGetDto> add(JobAdvertisePostDto jobAdvertisePostDto, int expiryInDays) {
        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfIdValid(jobAdvertisePostDto.getCityId(), "City"),
                businessRuleService.checkIfIdValid(jobAdvertisePostDto.getJobPositionId(), "Job"),
                businessRuleService.checkIfIdValid(jobAdvertisePostDto.getEmployerId(), "Employer"),
                businessRuleService.checkIfSalariesValid(jobAdvertisePostDto.getMaxSalary(), jobAdvertisePostDto.getMinSalary()),
                businessRuleService.checkIfOpenPositionValid(jobAdvertisePostDto.getOpenPositionCount()),
                businessRuleService.checkIfExpiryDayValid(expiryInDays),
                businessRuleService.checkIfBooleanValueTrue(
                        jobPositionService.existsById(jobAdvertisePostDto.getJobPositionId()),
                        "No job found with given id."
                ),
                businessRuleService.checkIfBooleanValueTrue(
                        employerService.existsById(jobAdvertisePostDto.getEmployerId()),
                        "No employer found with given id."
                )
        ));

        if(result.isSuccess()){
            JobAdvertise jobAdvertise = jobAdvertiseMapper.map(jobAdvertisePostDto);
            jobAdvertise.setExpirationDate(LocalDate.now().plusDays(expiryInDays));
            return new SuccessDataResult<>(
                    jobAdvertiseMapper.map(jobAdvertiseDao.save(jobAdvertise)),
                    "Job Advertise saved successfully."
            );
        } else {
            return new ErrorDataResult<>(result.getMessage());
        }
    }

    @Override
    public DataResult<List<JobAdvertiseGetDto>> findAllByActiveTrue() {
        return new SuccessDataResult<>(
                jobAdvertiseMapper.map(jobAdvertiseDao.findAllByActiveTrue()),
                "Data listed by active true");
    }

    @Override
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueSorted(int sortId) {
        if(sortId < 1 || sortId > 8){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("Sort Id should be between 1 and 8"));
        }
        JobAdvertiseSort[] jobAdvertiseSorts = JobAdvertiseSort.values(); // return JobAdvertiseSort enum array
        JobAdvertiseSort jobAdvertiseSort = jobAdvertiseSorts[sortId-1]; // take one that equals sortId
        Sort sort = Sort.by(jobAdvertiseSort.getDirection(), jobAdvertiseSort.getProperty()); // Create sort with enum

        return ResponseEntity.ok(new SuccessDataResult<>(
                jobAdvertiseMapper.map(jobAdvertiseDao.findAllByActiveTrue(sort)),
                "Data listed " + jobAdvertiseSort.name()
        ));
    }

    @Override
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndEmployerUuid(UUID uuid) {
        String regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(regexp);
        if(!pattern.matcher(uuid.toString()).matches()){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("UUID not valid."));
        }
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao.findAllByActiveTrueAndEmployerUuid(uuid);
        if(jobAdvertises.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new ErrorDataResult<>(
                            jobAdvertiseMapper.map(jobAdvertises),
                            "No Job Advertises found for given uuid.")
            );
        } else {
            return ResponseEntity.ok(new SuccessDataResult<>(
                    jobAdvertiseMapper.map(jobAdvertises),
                    "Data listed by employer"
            ));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndCityId(Short id) {
        DataResult result = businessRuleService.checkIfIdValid((long)id, "City");
        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao.findAllByActiveTrueAndCityId(id);
        if(jobAdvertises.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new ErrorDataResult<>(
                            jobAdvertiseMapper.map(jobAdvertises),
                    "No Job Advertises found for given city.")
            );
        } else {
            return ResponseEntity.badRequest().body(
                    new SuccessDataResult<>(
                            jobAdvertiseMapper.map(jobAdvertises),
                            "Data listed by city")
            );
        }
    }

    @Override
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndJobPositionId(Long id) {
        DataResult result = businessRuleService.checkIfIdValid(id, "Job");
        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao.findAllByActiveTrueAndJobPositionId(id);
        if(jobAdvertises.isEmpty()){
            return ResponseEntity.ok(
                    new ErrorDataResult<>(
                            jobAdvertiseMapper.map(jobAdvertises),
                            "No Job Advertises found for given job position.")
            );
        } else {
            return ResponseEntity.ok(
                    new SuccessDataResult<>(
                            jobAdvertiseMapper.map(jobAdvertises),
                            "Data listed by job position")
            );
        }
    }

    @Override
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>>
                findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(String companyName) {
        List<JobAdvertise> jobAdvertises = jobAdvertiseDao
                .findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(companyName);
        if(jobAdvertises.isEmpty()){
            return ResponseEntity.badRequest().body(
                    new ErrorDataResult<>(
                            jobAdvertiseMapper.map(jobAdvertises),
                            "No Job Advertises found for given company name.")
            );
        } else {
            return ResponseEntity.ok(new SuccessDataResult<>(
                    jobAdvertiseMapper.map(jobAdvertises),
                    "Data listed by company name"));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTruePaged(int pageNumber, int pageSize) {
        DataResult dataResult = businessRuleService.checkIfPageNoAndPageSizeValid(pageNumber, pageSize);
        if(dataResult.isSuccess()){
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    jobAdvertiseMapper.map(jobAdvertiseDao.findAll(pageable).getContent()),
                    "Data listed successfully. Page no:" + pageNumber +  " and Page size: " + pageSize
            ));
        } else {
            return ResponseEntity.badRequest().body(dataResult);
        }
    }

    @Override
    public ResponseEntity<Result> update(Long id, boolean active) {
        JobAdvertise jobAdvertise = jobAdvertiseDao.findById(id).orElse(null);
        if(jobAdvertise == null){
            return ResponseEntity.badRequest().body(
                    new ErrorResult("No job advertise found with given id")
            );
        }
        if(jobAdvertise.isActive() == active){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("Already " + active));
        } else {
            return ResponseEntity.ok(new SuccessResult("Job status successfully turned to " + active));
        }
    }
}