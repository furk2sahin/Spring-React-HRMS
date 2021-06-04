package kodlamaio.hrms.repositories.local;

import kodlamaio.hrms.model.concretes.local.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyDao extends JpaRepository<Faculty, Integer> {
    List<Faculty> findAllByUniversityId(Integer universityId);
}
