package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.JobAdvertise;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertiseGetDto;
import kodlamaio.hrms.model.dtos.concretes.JobAdvertisePostDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobAdvertiseMapper {

    @Mapping(source = "cityId", target = "city.id")
    @Mapping(source = "employerId", target = "employer.id")
    @Mapping(source = "jobPositionId", target = "jobPosition.id")
    JobAdvertise map(JobAdvertisePostDto jobAdvertisePostDto);

    @Named("toGetDto")
    JobAdvertiseGetDto map(JobAdvertise jobAdvertise);

    @IterableMapping(qualifiedByName = "toGetDto")
    List<JobAdvertiseGetDto> map(List<JobAdvertise> jobAdvertises);
}
