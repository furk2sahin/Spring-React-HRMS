package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.City;
import kodlamaio.hrms.model.dtos.concretes.CityGetDto;
import kodlamaio.hrms.model.dtos.concretes.CityPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Named("toEntity")
    City map(CityPostDto cityPostDto);

    @Named("toDto")
    CityGetDto map(City city);
}
