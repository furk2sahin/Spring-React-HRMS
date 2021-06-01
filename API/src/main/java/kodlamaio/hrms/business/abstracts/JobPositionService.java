package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.JobPosition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobPositionService {
    DataResult<List<JobPosition>> getAll();
    ResponseEntity<DataResult<JobPosition>> getByName(String name);
    ResponseEntity<DataResult<JobPosition>> add(JobPosition jobPosition);
    ResponseEntity<DataResult<List<JobPosition>>> getAllPaged(int pageNo, int pageSize);
}
