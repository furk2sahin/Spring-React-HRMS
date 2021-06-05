package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateLanguageGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateLanguagePostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateLanguageService {
    ResponseEntity<DataResult<CandidateLanguageGetDto>> add(CandidateLanguagePostDto candidateLanguagePostDto);
    ResponseEntity<DataResult<List<CandidateLanguageGetDto>>> getAll();
    boolean existsByCandidateCVIdAndLanguageId(Long cvId, Short languageId);
}
