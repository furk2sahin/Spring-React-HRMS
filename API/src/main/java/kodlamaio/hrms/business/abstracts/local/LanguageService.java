package kodlamaio.hrms.business.abstracts.local;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.local.Language;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LanguageService {
    ResponseEntity<DataResult<List<Language>>> findAll();
    ResponseEntity<DataResult<Language>> findById(Short id);
    boolean existsById(Short id);
}
