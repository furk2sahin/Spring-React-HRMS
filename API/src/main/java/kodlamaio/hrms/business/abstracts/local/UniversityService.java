package kodlamaio.hrms.business.abstracts.local;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.University;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UniversityService {
    ResponseEntity<DataResult<University>> findById(Integer id);
    ResponseEntity<DataResult<List<University>>> findByCityId(Short id);
}
