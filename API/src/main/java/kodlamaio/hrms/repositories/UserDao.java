package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.abstracts.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
