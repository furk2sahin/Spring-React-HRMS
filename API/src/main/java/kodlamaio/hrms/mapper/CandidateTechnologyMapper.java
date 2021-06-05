package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.cv.CandidateTechnology;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateTechnologyGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateTechnologyPostDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateTechnologyMapper {

    @Mapping(target = "candidateCV.id", source = "candidateCVId")
    CandidateTechnology map(CandidateTechnologyPostDto candidateTechnologyPostDto);

    @Mapping(target = "candidateCVId", source = "candidateCV.id")
    @Named("toGetDto")
    CandidateTechnologyGetDto map(CandidateTechnology candidateTechnology);

    @IterableMapping(qualifiedByName = "toGetDto")
    List<CandidateTechnologyGetDto> map(List<CandidateTechnology> candidateTechnologies);
}
