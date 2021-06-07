package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.concretes.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionDao extends JpaRepository<JobPosition, Long> {
    JobPosition getByJobName(String jobName);
    boolean existsByJobName(String jobName);
}
