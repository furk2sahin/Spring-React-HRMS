package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.dtos.concretes.EmployerGetDto;
import kodlamaio.hrms.model.dtos.concretes.EmployerPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employer")
@CrossOrigin
public class EmployersController {

    private final EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/get-all")
    public DataResult<List<EmployerGetDto>> getAll(){
        return employerService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<EmployerGetDto>> add(@RequestBody @Valid EmployerPostDto employerPostDto){
        return employerService.add(employerPostDto);
    }

    @GetMapping("/getAllPaged/{pageNumber}/{pageSize}")
    public ResponseEntity<DataResult<List<EmployerGetDto>>> getAllPaged(@PathVariable("pageNumber") int pageNumber,
                                                                  @PathVariable("pageSize") int pageSize){
        return employerService.getAllPaged(pageNumber, pageSize);
    }
}
