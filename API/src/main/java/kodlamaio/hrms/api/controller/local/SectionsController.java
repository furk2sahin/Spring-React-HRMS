package kodlamaio.hrms.api.controller.local;

import kodlamaio.hrms.business.abstracts.local.SectionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/section")
public class SectionsController {

    private SectionService sectionService;

    @Autowired
    public SectionsController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<DataResult<Section>> findById(@PathVariable("id") Integer id){
        return sectionService.findById(id);
    }

    @GetMapping("/find-by-faculty-id/{id}")
    public ResponseEntity<DataResult<List<Section>>> findByFacultyId(@PathVariable("id") Integer id){
        return sectionService.findByFacultyId(id);
    }
}
