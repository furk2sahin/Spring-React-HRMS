package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SystemPersonnelService {
    ResponseEntity<DataResult<SystemPersonnel>> add(SystemPersonnel systemPersonnel);
    ResponseEntity<DataResult<List<SystemPersonnel>>> getAllPaged(int pageNo, int pageSize);
}
