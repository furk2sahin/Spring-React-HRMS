package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.concretes.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateDao extends JpaRepository<Candidate, Long> {
    boolean existsByNationalIdentity(String nationalIdentity);
}
