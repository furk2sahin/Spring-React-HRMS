package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.concretes.JobAdvertise;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdvertiseDao extends JpaRepository<JobAdvertise, Long> {
    List<JobAdvertise> findAllByActiveTrue();
    List<JobAdvertise> findAllByActiveTrue(Sort sort);
    List<JobAdvertise> findAllByEmployerId(Long id);
    List<JobAdvertise> findAllByCityId(Long id);
    List<JobAdvertise> findAllByJobPositionId(Long id);
    List<JobAdvertise> findAllByEmployer_CompanyNameContainsIgnoreCase(String companyName);
}