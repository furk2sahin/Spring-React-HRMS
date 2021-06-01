package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.SystemPersonnel;
import kodlamaio.hrms.model.dtos.concretes.SystemPersonnelGetDto;
import kodlamaio.hrms.model.dtos.concretes.SystemPersonnelPostDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SystemPersonnelMapper {

    @Named("toEntity")
    SystemPersonnel map(SystemPersonnelPostDto systemPersonnelPostDto);

    @Named("toGetDto")
    SystemPersonnelGetDto map(SystemPersonnel systemPersonnel);

    @IterableMapping(qualifiedByName = "toGetDto")
    List<SystemPersonnelGetDto> map(List<SystemPersonnel> systemPersonnels);
}
