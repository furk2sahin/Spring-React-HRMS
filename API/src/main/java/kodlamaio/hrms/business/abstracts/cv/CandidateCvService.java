package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateCVGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateCVPostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CandidateCvService {
    ResponseEntity<DataResult<CandidateCVGetDto>> add(CandidateCVPostDto candidateCVPostDto);
    ResponseEntity<DataResult<List<CandidateCVGetDto>>> getAll();
    ResponseEntity<Result> updatePhoto(MultipartFile file, Long id);
    ResponseEntity<DataResult<List<CandidateCVGetDto>>> findAllByCandidateId(Long id);
    boolean existsById(Long id);
}
