package kodlamaio.hrms.api.controller.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateJobExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateJobExperienceGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateJobExperiencePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<DataResult<CandidateJobExperienceGetDto>> add(
            @RequestBody @Valid CandidateJobExperiencePostDto candidateJobExperiencePostDto){
        return candidateJobExperienceService.add(candidateJobExperiencePostDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<DataResult<List<CandidateJobExperienceGetDto>>> getAll(){
        return candidateJobExperienceService.getAll();
    }

    @GetMapping("/order-by-end-date-desc/{cvId}")
    public ResponseEntity<DataResult<List<CandidateJobExperienceGetDto>>> orderByEndDateDesc(@PathVariable("cvId") Long cvId){
        return candidateJobExperienceService.findAllByCandidateCVIdOrderByEndDateDesc(cvId);
    }
}
