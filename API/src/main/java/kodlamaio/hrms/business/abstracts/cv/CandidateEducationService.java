package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import org.springframework.http.ResponseEntity;

public interface CandidateEducationService {
    ResponseEntity<DataResult<CandidateEducation>> add(CandidateEducation candidateEducation);
}
