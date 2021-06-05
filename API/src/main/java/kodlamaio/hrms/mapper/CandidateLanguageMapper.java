package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.cv.CandidateLanguage;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateLanguageGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateLanguagePostDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateLanguageMapper {

    @Mapping(target = "candidateCV.id", source = "candidateCVId")
    @Mapping(target = "language.id", source = "languageId")
    CandidateLanguage map(CandidateLanguagePostDto candidateLanguagePostDto);

    @Mapping(target = "candidateCVId", source = "candidateCV.id")
    @Mapping(target = "language", source = "language.name")
    @Named("toGetDto")
    CandidateLanguageGetDto map(CandidateLanguage candidateLanguage);

    @IterableMapping(qualifiedByName = "toGetDto")
    List<CandidateLanguageGetDto> map(List<CandidateLanguage> candidateLanguages);
}
