package kodlamaio.hrms.api.controller.local;

import kodlamaio.hrms.business.abstracts.local.CityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CitiesController {

    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<DataResult<List<City>>> findAll(){
        return cityService.findAll();
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<DataResult<City>> findById(@PathVariable("id") Short id){
        return cityService.findById(id);
    }

}