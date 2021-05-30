package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.City;

import java.util.List;

public interface CityService {
    DataResult<List<City>> findAll();
}
