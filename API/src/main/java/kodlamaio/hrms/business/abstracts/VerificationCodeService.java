package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.abstracts.User;
import kodlamaio.hrms.model.concretes.VerificationCode;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface VerificationCodeService {
    VerificationCode add(User user);
    ResponseEntity<DataResult<VerificationCode>> findByUserUuid(UUID uuid);
    ResponseEntity<Result> confirm(VerificationCode code, String verificationCode);
}
