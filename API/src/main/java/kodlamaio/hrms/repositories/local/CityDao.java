package kodlamaio.hrms.repositories.local;

import kodlamaio.hrms.model.concretes.local.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Short> {
    boolean existsById(Short id);
}
