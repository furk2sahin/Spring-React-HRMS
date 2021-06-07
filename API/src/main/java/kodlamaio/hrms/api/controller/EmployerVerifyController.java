package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.EmployerVerifyService;
import kodlamaio.hrms.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employer-verify")
@CrossOrigin
public class EmployerVerifyController {

    private final EmployerVerifyService employerVerifyService;

    @Autowired
    public EmployerVerifyController(EmployerVerifyService employerVerifyService) {
        this.employerVerifyService = employerVerifyService;
    }

    @PostMapping("/verify/{employerUuid}/{personnelUuid}")
    public ResponseEntity<Result> verify(@PathVariable("employerUuid") UUID employerUuid,
                                         @PathVariable("personnelUuid") UUID personnelUuid){
        return employerVerifyService.verifyEmployer(employerUuid, personnelUuid);
    }
}
