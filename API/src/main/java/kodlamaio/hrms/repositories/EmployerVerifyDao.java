package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.concretes.EmployerVerify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployerVerifyDao extends JpaRepository<EmployerVerify, Long> {
    Optional<EmployerVerify> findByEmployerUuid(UUID uuid);
    boolean existsByEmployerUuidAndVerifiedTrue(UUID uuid); // when auth use this
}
