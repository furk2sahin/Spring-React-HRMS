package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/candidate")
public class CandidatesController {

    private final CandidateService candidateService;

    @Autowired
    public CandidatesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/get-all")
    public DataResult<List<Candidate>> getAll(){
        return candidateService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<Candidate>> add(@RequestBody @Valid Candidate candidate){
        return candidateService.add(candidate);
    }

    @GetMapping("/getAllPaged")
    public ResponseEntity<DataResult<List<Candidate>>> getAllPaged(@RequestParam("pageNumber") int pageNumber,
                                                                   @RequestParam("pageSize") int pageSize){
        return candidateService.getAllPaged(pageNumber, pageSize);
    }
}
