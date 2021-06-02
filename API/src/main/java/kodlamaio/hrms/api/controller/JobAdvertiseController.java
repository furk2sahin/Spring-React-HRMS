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

    @PostMapping("/add")
    public DataResult<JobAdvertiseGetDto> add(@RequestBody @Valid JobAdvertisePostDto jobAdvertisePostDto,
                                              @RequestParam("expiryInDays") int expiryInDays){
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
    @GetMapping("/findAllSorted")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueSorted(@RequestParam("sortId") int sortId){
        return jobAdvertiseService.findAllByActiveTrueSorted(sortId);
    }

    @GetMapping("/findAllByEmployerUuid")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndEmployerUuid(@RequestParam("uuid") UUID uuid){
        return jobAdvertiseService.findAllByActiveTrueAndEmployerUuid(uuid);
    }

    @GetMapping("/findAllByCityId")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndCityId(@RequestParam("id") Short id){
        return jobAdvertiseService.findAllByActiveTrueAndCityId(id);
    }

    @GetMapping("/findAllByJobPositionId")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndJobPositionId(@RequestParam("id") Long id){
        return jobAdvertiseService.findAllByActiveTrueAndJobPositionId(id);
    }

    @GetMapping("/findAllByCompanyNameContains")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(
            @RequestParam("name") String name){
        return jobAdvertiseService.findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(name);
    }

    @GetMapping("/findAllByActiveTruePaged")
    public ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTruePaged(
                                                                    @RequestParam("pageNumber") int pageNumber,
                                                                   @RequestParam("pageSize") int pageSize){
        return jobAdvertiseService.findAllByActiveTruePaged(pageNumber, pageSize);
    }


    @PutMapping("/updateStatus")
    public ResponseEntity<Result> update(@RequestParam("id") Long id,
                                         @RequestParam("active") boolean active){
        return jobAdvertiseService.update(id, active);
    }

}