package kodlamaio.hrms.api.controller.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateTechnologyService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateTechnology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidate-tech")
public class CandidateTechnologiesController {

    private CandidateTechnologyService candidateTechnologyService;

    @Autowired
    public CandidateTechnologiesController(CandidateTechnologyService candidateTechnologyService) {
        this.candidateTechnologyService = candidateTechnologyService;
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<CandidateTechnology>> add(@RequestBody CandidateTechnology candidateTechnology){
        return candidateTechnologyService.add(candidateTechnology);
    }

    @GetMapping("/get-all")
    public ResponseEntity<DataResult<List<CandidateTechnology>>> getAll(){
        return candidateTechnologyService.getAll();
    }
}
