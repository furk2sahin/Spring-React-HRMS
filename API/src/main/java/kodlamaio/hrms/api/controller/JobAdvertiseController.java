package kodlamaio.hrms.api.controller;

import kodlamaio.hrms.business.abstracts.JobAdvertiseService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.JobAdvertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/job-advertise")
public class JobAdvertiseController {

    private JobAdvertiseService jobAdvertiseService;

    @Autowired
    public JobAdvertiseController(JobAdvertiseService jobAdvertiseService) {
        this.jobAdvertiseService = jobAdvertiseService;
    }

    @GetMapping("/findAllByActiveTrue")
    DataResult<List<JobAdvertise>> findAllByActiveTrue(){
        return jobAdvertiseService.findAllByActiveTrue();
    }

    @PostMapping("/add")
    DataResult<JobAdvertise> add(@RequestBody @Valid JobAdvertise jobAdvertise){
        return jobAdvertiseService.add(jobAdvertise);
    }

    @GetMapping("/findAllSorted")
    DataResult<List<JobAdvertise>> findAllByActiveTrueSorted(@RequestParam("sortId") int sortId){
        return jobAdvertiseService.findAllByActiveTrueSorted(sortId);
    }

    @GetMapping("/findAllByEmployerUuid")
    DataResult<List<JobAdvertise>> findAllByActiveTrueAndEmployerUuid(@RequestParam("uuid") UUID uuid){
        return jobAdvertiseService.findAllByActiveTrueAndEmployerUuid(uuid);
    }

    @GetMapping("/findAllByCityId")
    DataResult<List<JobAdvertise>> findAllByActiveTrueAndCityId(@RequestParam("id") Long id){
        return jobAdvertiseService.findAllByActiveTrueAndCityId(id);
    }

    @GetMapping("/findAllByJobPositionId")
    DataResult<List<JobAdvertise>> findAllByActiveTrueAndJobPositionId(@RequestParam("id") Long id){
        return jobAdvertiseService.findAllByActiveTrueAndJobPositionId(id);
    }

    @GetMapping("/findAllByCompanyNameContains")
    DataResult<List<JobAdvertise>> findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(
            @RequestParam("name") String name){
        return jobAdvertiseService.findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(name);
    }
}
