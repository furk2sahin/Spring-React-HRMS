package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateCvService {
    ResponseEntity<DataResult<CandidateCV>> add(CandidateCV candidateCV, MultipartFile image);
    ResponseEntity<DataResult<List<CandidateCV>>> getAll();
    ResponseEntity<Result> updatePhoto(MultipartFile file, Long id);
    boolean existsById(Long id);
}
