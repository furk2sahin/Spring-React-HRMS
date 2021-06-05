package kodlamaio.hrms.repositories.cv;

import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateCvDao extends JpaRepository<CandidateCV, Long> {
    List<CandidateCV> findAllByActiveTrue();
}
