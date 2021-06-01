package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.concretes.JobAdvertise;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobAdvertiseDao extends JpaRepository<JobAdvertise, Long> {
    List<JobAdvertise> findAllByActiveTrue();
    List<JobAdvertise> findAllByActiveTrue(Sort sort);
    List<JobAdvertise> findAllByActiveTrueAndEmployerUuid(UUID uuid);
    List<JobAdvertise> findAllByActiveTrueAndCityId(Short id);
    List<JobAdvertise> findAllByActiveTrueAndJobPositionId(Long id);
    List<JobAdvertise> findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(String companyName);
}