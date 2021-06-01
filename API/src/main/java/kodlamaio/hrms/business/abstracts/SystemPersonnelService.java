package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import kodlamaio.hrms.model.dtos.concretes.SystemPersonnelGetDto;
import kodlamaio.hrms.model.dtos.concretes.SystemPersonnelPostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SystemPersonnelService {
    ResponseEntity<DataResult<SystemPersonnelGetDto>> add(SystemPersonnelPostDto systemPersonnelPostDto);
    ResponseEntity<DataResult<List<SystemPersonnelGetDto>>> getAllPaged(int pageNo, int pageSize);
}
