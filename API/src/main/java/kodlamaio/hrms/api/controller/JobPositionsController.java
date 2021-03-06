package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.JobPosition;
import kodlamaio.hrms.model.dtos.concretes.JobPositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job-positions")
@CrossOrigin
public class JobPositionsController {

    private final JobPositionService jobPositionService;

    @Autowired
    public JobPositionsController(JobPositionService jobPositionService){
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/get-all")
    public DataResult<List<JobPosition>> getAll(){
        return jobPositionService.getAll();
    }

    @GetMapping("/get-by-job-name/{jobName}")
    public ResponseEntity<DataResult<JobPosition>> getByJobName(@PathVariable("jobName") String jobName){
        return jobPositionService.getByName(jobName);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseEntity<DataResult<JobPosition>>> add(@Valid @RequestBody JobPositionDto jobPositionDto){
        return ResponseEntity.ok(jobPositionService.add(jobPositionDto));
    }

    @GetMapping("/getAllPaged/{pageNumber}/{pageSize}")
    public ResponseEntity<DataResult<List<JobPosition>>> getAllPaged(@PathVariable("pageNumber") int pageNumber,
                                                     @PathVariable("pageSize") int pageSize){
        return jobPositionService.getAllPaged(pageNumber, pageSize);
    }
}
