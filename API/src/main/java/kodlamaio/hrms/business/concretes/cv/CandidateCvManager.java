package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import kodlamaio.hrms.repositories.cv.CandidateCvDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateCvManager implements CandidateCvService {

    private CandidateCvDao candidateCvDao;

    @Autowired
    public CandidateCvManager(CandidateCvDao candidateCvDao) {
        this.candidateCvDao = candidateCvDao;
    }

    @Override
    public ResponseEntity<DataResult<CandidateCV>> add(CandidateCV candidateCV) {
        candidateCV.getCandidateEducations().forEach(candidateEducation -> candidateEducation.setCandidateCV(candidateCV));
        return ResponseEntity.ok(new SuccessDataResult<>(candidateCvDao.save(candidateCV)));
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateCV>>> getAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(candidateCvDao.findAll()));
    }
}
