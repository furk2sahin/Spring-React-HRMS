package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.dtos.concretes.EmployerGetDto;
import kodlamaio.hrms.model.dtos.concretes.EmployerPostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployerService {
    DataResult<List<EmployerGetDto>> getAll();
    ResponseEntity<DataResult<EmployerGetDto>> add(EmployerPostDto employerPostDto);
    ResponseEntity<DataResult<List<EmployerGetDto>>> getAllPaged(int pageNo, int pageSize);
}
