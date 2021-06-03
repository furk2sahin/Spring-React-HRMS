package kodlamaio.hrms.api.controller.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidate-cv")
public class CandidateCvController {

    private final CandidateCvService candidateCvService;

    @Autowired
    public CandidateCvController(CandidateCvService candidateCvService) {
        this.candidateCvService = candidateCvService;
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<CandidateCV>> add(@RequestBody CandidateCV candidateCV){
        return candidateCvService.add(candidateCV);
    }

    @GetMapping("/get-all")
    public ResponseEntity<DataResult<List<CandidateCV>>> add(){
        return candidateCvService.getAll();
    }
}
