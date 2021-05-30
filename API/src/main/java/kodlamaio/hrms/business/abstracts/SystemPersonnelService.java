package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.SystemPersonnel;

import java.util.List;

public interface SystemPersonnelService {
    DataResult<SystemPersonnel> add(SystemPersonnel systemPersonnel);
    DataResult<List<SystemPersonnel>> getAllPaged(int pageNo, int pageSize);
}
