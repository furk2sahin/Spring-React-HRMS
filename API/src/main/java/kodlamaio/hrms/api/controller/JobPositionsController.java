package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.JobPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/job-positions")
public class JobPositionsController {

    private JobPositionService jobPositionService;

    @Autowired
    public JobPositionsController(JobPositionService jobPositionService){
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/get-all")
    public DataResult<List<JobPosition>> getAll(){
        return jobPositionService.getAll();
    }

    @GetMapping("/get-by-job-name/{jobName}")
    public DataResult<JobPosition> getByJobName(@PathVariable("jobName") String jobName){
        return jobPositionService.getByName(jobName);
    }

    @PostMapping("/add-job-position")
    public ResponseEntity<DataResult<JobPosition>> add(@Valid @RequestBody JobPosition jobPosition){
        return ResponseEntity.ok(jobPositionService.add(jobPosition));
    }
}
