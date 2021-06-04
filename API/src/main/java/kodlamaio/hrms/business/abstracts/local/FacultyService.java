package kodlamaio.hrms.business.abstracts.local;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.Faculty;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FacultyService {
    ResponseEntity<DataResult<Faculty>> findById(Integer id);
    ResponseEntity<DataResult<List<Faculty>>> findByUniversityId(Integer id);
}
