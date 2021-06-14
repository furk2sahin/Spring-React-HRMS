package kodlamaio.hrms.api.controller.local;

import kodlamaio.hrms.business.abstracts.local.LanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/languages")
@CrossOrigin
public class LanguageController {

    private LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/get-all")
    ResponseEntity<DataResult<List<Language>>> findAll(){
        return languageService.findAll();
    }

    @GetMapping("/find-by-id")
    ResponseEntity<DataResult<Language>> findById(Short id){
        return languageService.findById(id);
    }

}
