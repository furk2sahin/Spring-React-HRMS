package kodlamaio.hrms.business.concretes.local;

import kodlamaio.hrms.business.abstracts.local.LanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.local.Language;
import kodlamaio.hrms.repositories.local.LanguageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageManager implements LanguageService {

    private LanguageDao languageDao;

    @Autowired
    public LanguageManager(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public ResponseEntity<DataResult<List<Language>>> findAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(
                languageDao.findAll(Sort.by(Sort.Direction.ASC, "name")),
                "Languages listed successfully!"
        ));
    }

    @Override
    public ResponseEntity<DataResult<Language>> findById(Short id) {
        Language language = languageDao.findById(id).orElse(null);
        if(language == null){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("No city found with given id"));
        }
        return ResponseEntity.ok(new SuccessDataResult<>(language, "City found."));
    }

    @Override
    public boolean existsById(Short id) {
        return languageDao.existsById(id);
    }
}
