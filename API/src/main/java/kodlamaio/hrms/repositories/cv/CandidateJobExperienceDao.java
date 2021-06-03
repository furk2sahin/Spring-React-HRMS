package kodlamaio.hrms.repositories.cv;

import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateJobExperienceDao extends JpaRepository<CandidateJobExperience, Long> {
}
