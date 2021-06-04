package kodlamaio.hrms.business.concretes.local;

import kodlamaio.hrms.business.abstracts.local.CityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.local.City;
import kodlamaio.hrms.repositories.local.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {

    private final CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public ResponseEntity<DataResult<List<City>>> findAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(cityDao.findAll(), "Cities listed successfully!"));
    }

    @Override
    public ResponseEntity<DataResult<City>> findById(Short id) {
        City city = cityDao.findById(id).orElse(null);
        if(city == null){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("No city found with given id"));
        }
        return ResponseEntity.ok(new SuccessDataResult<>(city, "City found."));
    }
}
