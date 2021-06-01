package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.JobAdvertise;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface JobAdvertiseService {
    DataResult<JobAdvertise> add(JobAdvertise jobAdvertise, int expiryInDays);
    DataResult<List<JobAdvertise>> findAllByActiveTrue();
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueSorted(int sortId);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueAndEmployerUuid(UUID uuid);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueAndCityId(Long id);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueAndJobPositionId(Long id);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(String companyName);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTruePaged(int pageNumber, int pageSize);
    ResponseEntity<DataResult<JobAdvertise>> update(Long id, boolean active);
}
