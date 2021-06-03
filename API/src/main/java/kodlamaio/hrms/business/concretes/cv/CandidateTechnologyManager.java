package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateTechnologyService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateTechnology;
import kodlamaio.hrms.repositories.cv.CandidateTechnologyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateTechnologyManager implements CandidateTechnologyService {

    private CandidateTechnologyDao candidateTechnologyDao;

    @Autowired
    public CandidateTechnologyManager(CandidateTechnologyDao candidateTechnologyDao) {
        this.candidateTechnologyDao = candidateTechnologyDao;
    }

    @Override
    public ResponseEntity<DataResult<CandidateTechnology>> add(CandidateTechnology candidateTechnology) {
        return ResponseEntity.ok(new SuccessDataResult<>(candidateTechnologyDao.save(candidateTechnology)));
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateTechnology>>> getAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(candidateTechnologyDao.findAll()));
    }
}
