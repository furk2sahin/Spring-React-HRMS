package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.Employer;
import kodlamaio.hrms.model.dtos.concretes.EmployerGetDto;
import kodlamaio.hrms.model.dtos.concretes.EmployerPostDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployerMapper {

    @Named("employerPostDtoToEmployer")
    Employer employerPostDtoToEmployer(EmployerPostDto employerPostDto);

    @Named("employerToEmployerGetDto")
    EmployerGetDto employerToEmployerGetDto(Employer employer);

    @IterableMapping(qualifiedByName = "employerToEmployerGetDto")
    List<EmployerGetDto> employersToEmployerGetDtos(List<Employer> employers);
}
