package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.JobAdvertise;

import java.util.List;
import java.util.UUID;

public interface JobAdvertiseService {
    DataResult<JobAdvertise> add(JobAdvertise jobAdvertise);
    DataResult<List<JobAdvertise>> findAllByActiveTrue();
    DataResult<List<JobAdvertise>> findAllByActiveTrueSorted(int sortId);
    DataResult<List<JobAdvertise>> findAllByActiveTrueAndEmployerUuid(UUID uuid);
    DataResult<List<JobAdvertise>> findAllByActiveTrueAndCityId(Long id);
    DataResult<List<JobAdvertise>> findAllByActiveTrueAndJobPositionId(Long id);
    DataResult<List<JobAdvertise>> findAllByActiveTrueAndEmployer_CompanyNameContainsIgnoreCase(String companyName);
}
