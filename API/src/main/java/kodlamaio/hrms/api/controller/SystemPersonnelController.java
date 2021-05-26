package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.SystemPersonnelService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/system-personnel")
public class SystemPersonnelController {

    private SystemPersonnelService personnelService;

    @Autowired
    public SystemPersonnelController(SystemPersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid SystemPersonnel systemPersonnel){
        return personnelService.add(systemPersonnel);
    }
}
