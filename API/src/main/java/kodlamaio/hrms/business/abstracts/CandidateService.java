package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.Candidate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateService {
    ResponseEntity<DataResult<Candidate>> add(Candidate candidate);
    DataResult<List<Candidate>> getAll();
    ResponseEntity<DataResult<List<Candidate>>> getAllPaged(int pageNo, int pageSize);
}
