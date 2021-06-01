package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.JobAdvertise;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertiseGetDto;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertisePostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface JobAdvertiseService {
    DataResult<JobAdvertiseGetDto> add(JobAdvertisePostDto jobAdvertisePostDto, int expiryInDays);
    DataResult<List<JobAdvertise>> findAllByActiveTrue();
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueSorted(int sortId);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueAndEmployerUuid(UUID uuid);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueAndCityId(Short id);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueAndJobPositionId(Long id);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(String companyName);
    ResponseEntity<DataResult<List<JobAdvertise>>> findAllByActiveTruePaged(int pageNumber, int pageSize);
    ResponseEntity<DataResult<JobAdvertise>> update(Long id, boolean active);
}
