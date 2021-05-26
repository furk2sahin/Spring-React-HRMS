package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.concretes.SystemPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SystemPersonnelDao extends JpaRepository<SystemPersonnel, Long> {
    Optional<SystemPersonnel> findByUuid(UUID uuid);
}
