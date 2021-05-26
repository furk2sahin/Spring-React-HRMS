package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.SystemPersonnel;

public interface SystemPersonnelService {
    DataResult<SystemPersonnel> add(SystemPersonnel systemPersonnel);
}
