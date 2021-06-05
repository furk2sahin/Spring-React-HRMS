package kodlamaio.hrms.api.controller.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateLanguage;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateLanguageGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateLanguagePostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<DataResult<CandidateLanguageGetDto>> add(
            @RequestBody @Valid CandidateLanguagePostDto candidateLanguagePostDto){
        return candidateLanguageService.add(candidateLanguagePostDto);
    }

    @GetMapping("/get-all")
    public ResponseEntity<DataResult<List<CandidateLanguageGetDto>>> getAll(){
        return candidateLanguageService.getAll();
    }
}
