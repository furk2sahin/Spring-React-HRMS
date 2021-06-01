package kodlamaio.hrms.repositories;

import kodlamaio.hrms.model.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Short> {
}
