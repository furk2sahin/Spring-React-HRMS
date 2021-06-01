package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.SystemPersonnelService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/system-personnel")
public class SystemPersonnelController {

    private final SystemPersonnelService personnelService;

    @Autowired
    public SystemPersonnelController(SystemPersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<SystemPersonnel>> add(@RequestBody @Valid SystemPersonnel systemPersonnel){
        return personnelService.add(systemPersonnel);
    }

    @GetMapping("/getAllPaged")
    public ResponseEntity<DataResult<List<SystemPersonnel>>> getAllPaged(@RequestParam("pageNumber") int pageNumber,
                                                     @RequestParam("pageSize") int pageSize){
        return personnelService.getAllPaged(pageNumber, pageSize);
    }
}
