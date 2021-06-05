package kodlamaio.hrms.repositories.cv;

import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateEducationDao extends JpaRepository<CandidateEducation, Long> {
    List<CandidateEducation> findAllByCandidateCVIdOrderByEndDateDesc(Long cvId);
    boolean existsByCandidateCVIdAndSectionIdAndCandidateEducationDegreeId(Long candidateCvId,
                                                                               Integer sectionId,
                                                                               byte degreeId);
}
