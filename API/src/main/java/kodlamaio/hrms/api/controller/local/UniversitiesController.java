package kodlamaio.hrms.api.controller.local;

import kodlamaio.hrms.business.abstracts.local.UniversityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.Faculty;
import kodlamaio.hrms.model.concretes.local.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/university")
@CrossOrigin
public class UniversitiesController {

    private UniversityService universityService;

    @Autowired
    public UniversitiesController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<DataResult<University>> findById(@PathVariable("id") Integer id){
        return universityService.findById(id);
    }

    @GetMapping("/find-by-city-id/{id}")
    ResponseEntity<DataResult<List<University>>> findByCityId(@PathVariable("id") Short id){
        return universityService.findByCityId(id);
    }
}
