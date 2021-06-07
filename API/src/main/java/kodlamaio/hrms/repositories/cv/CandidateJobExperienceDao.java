package kodlamaio.hrms.repositories.cv;

import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateJobExperienceDao extends JpaRepository<CandidateJobExperience, Long> {
    List<CandidateJobExperience> findAllByCandidateCVIdOrderByEndDateDesc(Long cvId);
    boolean existsByCandidateCVIdAndCompanyNameAndJobPositionId(Long candidateCVId,
                                                                   String companyName,
                                                                   Long jobPositionId);
}
