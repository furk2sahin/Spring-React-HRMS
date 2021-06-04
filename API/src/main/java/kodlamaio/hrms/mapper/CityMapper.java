package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.local.City;
import kodlamaio.hrms.model.dtos.concretes.CityGetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CityMapper {
    @Named("toDto")
    CityGetDto map(City city);
}
