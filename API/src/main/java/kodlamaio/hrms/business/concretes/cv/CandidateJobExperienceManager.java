package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateJobExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import kodlamaio.hrms.repositories.cv.CandidateJobExperienceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateJobExperienceManager implements CandidateJobExperienceService {

    private CandidateJobExperienceDao candidateJobExperienceDao;

    @Autowired
    public CandidateJobExperienceManager(CandidateJobExperienceDao candidateJobExperienceDao) {
        this.candidateJobExperienceDao = candidateJobExperienceDao;
    }

    @Override
    public ResponseEntity<DataResult<CandidateJobExperience>> add(CandidateJobExperience candidateJobExperience) {
        return ResponseEntity.ok(new SuccessDataResult<>(candidateJobExperienceDao.save(candidateJobExperience)));
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateJobExperience>>> getAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(candidateJobExperienceDao.findAll()));
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateJobExperience>>> findAllByCandidateCVIdOrderByEndDateDesc(Long cvId) {
        return ResponseEntity.ok(new SuccessDataResult<>(
                candidateJobExperienceDao.findAllByCandidateCVIdOrderByEndDateDesc(cvId),
                "Data listed by end date desc"
        ));
    }
}
