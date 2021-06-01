package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.Employer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployerService {
    DataResult<List<Employer>> getAll();
    ResponseEntity<DataResult<Employer>> add(Employer employer);
    ResponseEntity<DataResult<List<Employer>>> getAllPaged(int pageNo, int pageSize);
}
