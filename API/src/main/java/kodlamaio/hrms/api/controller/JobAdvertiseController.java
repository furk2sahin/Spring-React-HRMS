package kodlamaio.hrms.api.controller;

import io.swagger.annotations.ApiOperation;
import kodlamaio.hrms.business.abstracts.JobAdvertiseService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertiseGetDto;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertisePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/job-advertise")
@CrossOrigin
public class JobAdvertiseController {

    private final JobAdvertiseService jobAdvertiseService;

    @Autowired
    public JobAdvertiseController(JobAdvertiseService jobAdvertiseService) {
        this.jobAdvertiseService = jobAdvertiseService;
    }

    @GetMapping("/findAllByActiveTrue")
    public DataResult<List<JobAdvertiseGetDto>> findAllByActiveTrue(){
        return jobAdvertiseService.findAllByActiveTrue();
    }

    @PostMapping("/add/{expiryInDays}")
    public DataResult<JobAdvertiseGetDto> add(@RequestBody @Valid JobAdvertisePostDto jobAdvertisePostDto,
                                              @PathVariable("expiryInDays") int expiryInDays){
        return jobAdvertiseService.add(jobAdvertisePostDto, expiryInDays);
    }

    @ApiOperation(value = "sortId=1 -> sort BY_CREATE_DATE_DESC ----- " +
            "sortId=2 -> sort BY_CREATE_DATE_ASC ----- " +
            "sortId=3 -> for sort BY_MAX_SALARY_DESC ----- " +
            "sortId=4 -> for sort BY_MAX_SALARY_ASC ----- " +
            "sortId=5 -> for sort BY_MIN_SALARY_DESC ----- " +
            "sortId=6 -> for sort BY_MIN_SALARY_ASC ----- " +
            "sortId=7 -> for sort BY_EXPIRATION_DATE_DESC ----- " +
            "sortId=8 -> for sort BY_EXPIRATION_DATE_ASC")
    @GetMapping("/findAllSorted/{sortId}")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueSorted(@PathVariable("sortId") int sortId){
        return jobAdvertiseService.findAllByActiveTrueSorted(sortId);
    }

    @GetMapping("/findAllByEmployerUuid/{uuid}")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndEmployerUuid(@PathVariable("uuid") UUID uuid){
        return jobAdvertiseService.findAllByActiveTrueAndEmployerUuid(uuid);
    }

    @GetMapping("/findAllByCityId/{id}")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndCityId(@PathVariable("id") Short id){
        return jobAdvertiseService.findAllByActiveTrueAndCityId(id);
    }

    @GetMapping("/findAllByJobPositionId/{id}")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndJobPositionId(@PathVariable("id") Long id){
        return jobAdvertiseService.findAllByActiveTrueAndJobPositionId(id);
    }

    @GetMapping("/findAllByCompanyNameContains/{name}")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(
            @PathVariable("name") String name){
        return jobAdvertiseService.findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(name);
    }

    @GetMapping("/findAllByActiveTruePaged/{pageNumber}/{pageSize}")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTruePaged(
                                                                    @PathVariable("pageNumber") int pageNumber,
                                                                   @PathVariable("pageSize") int pageSize){
        return jobAdvertiseService.findAllByActiveTruePaged(pageNumber, pageSize);
    }


    @PutMapping("/updateStatus/{id}/{active}")
    public ResponseEntity<Result> update(@PathVariable("id") Long id,
                                         @PathVariable("active") boolean active){
        return jobAdvertiseService.update(id, active);
    }

}