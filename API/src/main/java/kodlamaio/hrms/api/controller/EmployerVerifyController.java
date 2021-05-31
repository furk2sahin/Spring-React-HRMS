package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.EmployerVerifyService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.EmployerVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employer-verify")
public class EmployerVerifyController {

    private EmployerVerifyService employerVerifyService;

    @Autowired
    public EmployerVerifyController(EmployerVerifyService employerVerifyService) {
        this.employerVerifyService = employerVerifyService;
    }

    @PostMapping("/verify/{employerUuid}/{personnelUuid}")
    public DataResult<EmployerVerify> verify(@PathVariable("employerUuid") UUID employerUuid,
                                             @PathVariable("personnelUuid") UUID personnelUuid){
        return employerVerifyService.verifyEmployer(employerUuid, personnelUuid);
    }
}
