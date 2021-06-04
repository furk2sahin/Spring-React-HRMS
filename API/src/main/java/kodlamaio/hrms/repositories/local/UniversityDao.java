package kodlamaio.hrms.repositories.local;

import kodlamaio.hrms.model.concretes.local.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityDao extends JpaRepository<University, Integer> {
    List<University> findAllByCityId(Short cityId);
}
