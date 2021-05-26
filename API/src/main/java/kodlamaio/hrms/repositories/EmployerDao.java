package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployerDao extends JpaRepository<Employer, Long> {
    Optional<Employer> findByUuid(UUID uuid);
}
