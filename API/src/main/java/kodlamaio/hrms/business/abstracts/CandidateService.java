package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.dtos.concretes.CandidateGetDto;
import kodlamaio.hrms.model.dtos.concretes.CandidatePostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateService {
    ResponseEntity<DataResult<CandidateGetDto>> add(CandidatePostDto candidatePostDto);
    DataResult<List<CandidateGetDto>> getAll();
    ResponseEntity<DataResult<List<CandidateGetDto>>> getAllPaged(int pageNo, int pageSize);
}
