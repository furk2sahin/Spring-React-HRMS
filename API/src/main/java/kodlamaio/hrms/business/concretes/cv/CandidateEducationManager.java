package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateEducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import kodlamaio.hrms.repositories.cv.CandidateEducationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CandidateEducationManager implements CandidateEducationService {

    private final CandidateEducationDao candidateEducationDao;

    @Autowired
    public CandidateEducationManager(CandidateEducationDao candidateEducationDao) {
        this.candidateEducationDao = candidateEducationDao;
    }

    @Override
    public ResponseEntity<DataResult<CandidateEducation>> add(CandidateEducation candidateEducation) {
        return ResponseEntity.ok(new SuccessDataResult<>(candidateEducationDao.save(candidateEducation)));
    }
}
