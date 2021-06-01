package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.Employer;
import kodlamaio.hrms.model.concretes.EmployerVerify;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface EmployerVerifyService {
    DataResult<EmployerVerify> add(Employer employer);
    ResponseEntity<DataResult<EmployerVerify>> verifyEmployer(UUID employerUuid, UUID systemPersonnelUuid);
}
