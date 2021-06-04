package kodlamaio.hrms.business.abstracts.local;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.Section;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SectionService {
    ResponseEntity<DataResult<Section>> findById(Integer id);
    ResponseEntity<DataResult<List<Section>>> findByFacultyId(Integer id);
}
