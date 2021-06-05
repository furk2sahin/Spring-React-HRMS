package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateTechnologyGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateTechnologyPostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateTechnologyService {
    ResponseEntity<DataResult<CandidateTechnologyGetDto>> add(CandidateTechnologyPostDto candidateTechnologyPostDto);
    ResponseEntity<DataResult<List<CandidateTechnologyGetDto>>> getAll();
    boolean existsByCandidateCVIdAndTechnologyName(Long id, String name);

}
