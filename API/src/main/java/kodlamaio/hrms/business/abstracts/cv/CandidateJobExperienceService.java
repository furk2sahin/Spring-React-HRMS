package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateJobExperienceService {
    ResponseEntity<DataResult<CandidateJobExperience>> add(CandidateJobExperience candidateJobExperience);
    ResponseEntity<DataResult<List<CandidateJobExperience>>> getAll();
    ResponseEntity<DataResult<List<CandidateJobExperience>>> findAllByCandidateCVIdOrderByEndDateDesc(Long cvId);
}
