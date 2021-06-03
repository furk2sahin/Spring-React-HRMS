package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateLanguage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateLanguageService {
    ResponseEntity<DataResult<CandidateLanguage>> add(CandidateLanguage candidateLanguage);
    ResponseEntity<DataResult<List<CandidateLanguage>>> getAll();
}
