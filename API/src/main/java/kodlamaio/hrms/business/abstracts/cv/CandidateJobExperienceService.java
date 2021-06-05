package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateJobExperienceGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateJobExperiencePostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateJobExperienceService {
    ResponseEntity<DataResult<CandidateJobExperienceGetDto>> add(CandidateJobExperiencePostDto CandidateJobExperiencePostDto);
    ResponseEntity<DataResult<List<CandidateJobExperienceGetDto>>> getAll();
    ResponseEntity<DataResult<List<CandidateJobExperienceGetDto>>> findAllByCandidateCVIdOrderByEndDateDesc(Long cvId);
    boolean existsByCandidateCVIdAndAndCompanyNameAndJobPositionId(Long candidateCVId,
                                                                   String companyName,
                                                                   Long jobPositionId);
}
