package kodlamaio.hrms.api.controller.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateJobExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job-experience")
public class CandidateJobExperiencesController {

    private final CandidateJobExperienceService candidateJobExperienceService;

    @Autowired
    public CandidateJobExperiencesController(CandidateJobExperienceService candidateJobExperienceService) {
        this.candidateJobExperienceService = candidateJobExperienceService;
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<CandidateJobExperience>> add(
            @RequestBody CandidateJobExperience candidateJobExperience){
        return candidateJobExperienceService.add(candidateJobExperience);
    }

    @GetMapping("/get-all")
    public ResponseEntity<DataResult<List<CandidateJobExperience>>> getAll(){
        return candidateJobExperienceService.getAll();
    }
}
