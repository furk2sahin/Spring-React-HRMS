package kodlamaio.hrms.core.dataAccess;

import kodlamaio.hrms.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
