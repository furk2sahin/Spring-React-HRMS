package kodlamaio.hrms.repositories.cv;

import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateEducationDao extends JpaRepository<CandidateEducation, Long> {
}
