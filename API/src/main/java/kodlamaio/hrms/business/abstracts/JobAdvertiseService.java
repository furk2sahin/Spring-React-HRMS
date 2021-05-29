package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.JobAdvertise;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface JobAdvertiseService {
    DataResult<JobAdvertise> add(JobAdvertise jobAdvertise);
    DataResult<List<JobAdvertise>> findAllByActiveTrue();
    DataResult<List<JobAdvertise>> findAllByActiveTrue(Sort sort);
    DataResult<List<JobAdvertise>> findAllByEmployerId(Long id);
    DataResult<List<JobAdvertise>> findAllByCityId(Long id);
    DataResult<List<JobAdvertise>> findAllByJobPositionId(Long id);
    DataResult<List<JobAdvertise>> findAllByEmployer_CompanyNameContainsIgnoreCase(String companyName);
}
