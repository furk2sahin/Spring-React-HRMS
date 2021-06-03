package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateTechnology;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateTechnologyService {
    ResponseEntity<DataResult<CandidateTechnology>> add(CandidateTechnology candidateTechnology);
    ResponseEntity<DataResult<List<CandidateTechnology>>> getAll();
}
