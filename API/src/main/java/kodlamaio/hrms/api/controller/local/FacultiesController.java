package kodlamaio.hrms.api.controller.local;

import kodlamaio.hrms.business.abstracts.local.FacultyService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faculty")
@CrossOrigin
public class FacultiesController {

    private FacultyService facultyService;

    @Autowired
    public FacultiesController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<DataResult<Faculty>> findById(@PathVariable("id") Integer id){
        return facultyService.findById(id);
    }

    @GetMapping("/find-by-university-id/{id}")
    ResponseEntity<DataResult<List<Faculty>>> findByUniversityId(@PathVariable("id") Integer id){
        return facultyService.findByUniversityId(id);
    }
}
