package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.concretes.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VerificationCodeDao extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findByUserUuid(UUID uuid);
}
