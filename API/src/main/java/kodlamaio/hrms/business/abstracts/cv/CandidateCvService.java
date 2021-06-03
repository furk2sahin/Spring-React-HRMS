package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateCvService {
    ResponseEntity<DataResult<CandidateCV>> add(CandidateCV candidateCV);
    ResponseEntity<DataResult<List<CandidateCV>>> getAll();
}
