package kodlamaio.hrms.business.abstracts.local;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.City;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {
    ResponseEntity<DataResult<List<City>>> findAll();
    ResponseEntity<DataResult<City>> findById(Short id);
}
