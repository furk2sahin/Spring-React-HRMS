package kodlamaio.hrms.api.controller.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidate-language")
public class CandidateLanguagesController {

    private CandidateLanguageService candidateLanguageService;

    @Autowired
    public CandidateLanguagesController(CandidateLanguageService candidateLanguageService) {
        this.candidateLanguageService = candidateLanguageService;
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<CandidateLanguage>> add(@RequestBody CandidateLanguage candidateLanguage){
        return candidateLanguageService.add(candidateLanguage);
    }

    @GetMapping("/get-all")
    public ResponseEntity<DataResult<List<CandidateLanguage>>> getAll(){
        return candidateLanguageService.getAll();
    }
}
