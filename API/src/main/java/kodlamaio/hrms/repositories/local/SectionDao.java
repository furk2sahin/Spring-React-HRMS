package kodlamaio.hrms.repositories.local;

import kodlamaio.hrms.model.concretes.local.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionDao extends JpaRepository<Section, Integer> {
    List<Section> findAllByFacultyId(Integer facultyId);
}
