package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.abstracts.User;
import kodlamaio.hrms.model.concretes.VerificationCode;

import java.util.UUID;

public interface VerificationCodeService {
    VerificationCode add(User user);
    DataResult<VerificationCode> findByUserUuid(UUID uuid);
    Result update(VerificationCode code, String verificationCode);
}
