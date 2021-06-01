package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertiseGetDto;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertisePostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface JobAdvertiseService {
    DataResult<JobAdvertiseGetDto> add(JobAdvertisePostDto jobAdvertisePostDto, int expiryInDays);
    DataResult<List<JobAdvertiseGetDto>> findAllByActiveTrue();
    ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueSorted(int sortId);
    ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndEmployerUuid(UUID uuid);
    ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndCityId(Short id);
    ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndJobPositionId(Long id);
    ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(String companyName);
    ResponseEntity<DataResult<List<JobAdvertiseGetDto>>> findAllByActiveTruePaged(int pageNumber, int pageSize);
    ResponseEntity<Result> update(Long id, boolean active);
}
