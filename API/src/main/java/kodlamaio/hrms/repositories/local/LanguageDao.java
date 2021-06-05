package kodlamaio.hrms.repositories.local;

import kodlamaio.hrms.model.concretes.local.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDao extends JpaRepository<Language, Short> {
    boolean existsById(Short id);
}
