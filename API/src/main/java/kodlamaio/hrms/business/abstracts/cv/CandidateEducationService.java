package kodlamaio.hrms.business.abstracts.cv;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateEducationGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateEducationPostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CandidateEducationService {
    ResponseEntity<DataResult<CandidateEducationGetDto>> add(CandidateEducationPostDto candidateEducationPostDto);
    ResponseEntity<DataResult<List<CandidateEducationGetDto>>> findAllByCandidateCVIdOrderByEndDateDesc(Long cvId);
    boolean existsByCandidateCVIdAndSectionIdAndCandidateEducationDegreeId(Long candidateCvId,
                                                                               Integer sectionId,
                                                                               byte degreeId);
}
