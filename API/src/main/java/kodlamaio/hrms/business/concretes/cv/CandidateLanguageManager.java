package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.cv.CandidateLanguage;
import kodlamaio.hrms.repositories.cv.CandidateLanguageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateLanguageManager implements CandidateLanguageService {

    private CandidateLanguageDao candidateLanguageDao;

    @Autowired
    public CandidateLanguageManager(CandidateLanguageDao candidateLanguageDao) {
        this.candidateLanguageDao = candidateLanguageDao;
    }

    @Override
    public ResponseEntity<DataResult<CandidateLanguage>> add(CandidateLanguage candidateLanguage) {
        return ResponseEntity.ok(new SuccessDataResult<>(candidateLanguageDao.save(candidateLanguage)));
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateLanguage>>> getAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(candidateLanguageDao.findAll()));
    }
}
