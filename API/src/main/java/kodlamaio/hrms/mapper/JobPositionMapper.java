package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.JobPosition;
import kodlamaio.hrms.model.dtos.concretes.JobPositionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface JobPositionMapper {

    @Named("toEntity")
    JobPosition map(JobPositionDto jobPositionDto);
}
