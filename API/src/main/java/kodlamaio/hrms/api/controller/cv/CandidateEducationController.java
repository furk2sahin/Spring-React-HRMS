package kodlamaio.hrms.api.controller.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateEducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateEducationGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateEducationPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/candidate-education")
@CrossOrigin
public class CandidateEducationController {

    private CandidateEducationService candidateEducationService;

    @Autowired
    public CandidateEducationController(CandidateEducationService candidateEducationService) {
        this.candidateEducationService = candidateEducationService;
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<CandidateEducationGetDto>> add(
            @RequestBody @Valid CandidateEducationPostDto candidateEducationPostDto){
        return candidateEducationService.add(candidateEducationPostDto);
    }

    @GetMapping("/order-by-end-date-desc/{cvId}")
    public ResponseEntity<DataResult<List<CandidateEducationGetDto>>> orderByEndDateDesc(@PathVariable("cvId") Long cvId){
        return candidateEducationService.findAllByCandidateCVIdOrderByEndDateDesc(cvId);
    }
}
