package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.EmployerVerify;

import java.util.UUID;

public interface EmployerVerifyService {
    DataResult<EmployerVerify> verifyEmployer(UUID employerUuid, UUID systemPersonnelUuid);
}
