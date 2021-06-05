package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import kodlamaio.hrms.model.concretes.cv.CandidateLanguage;
import kodlamaio.hrms.model.concretes.cv.CandidateTechnology;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import kodlamaio.hrms.model.dtos.concretes.cv.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateCVMapper {

    @Mapping(source = "candidateEducationDegree.degree", target = "degreeName")
    @Mapping(source = "candidateEducationGrade.grade", target = "gradeName")
    @Mapping(source = "candidateCV.id", target = "candidateCVId")
    @Named("toEducationDto")
    CandidateEducationGetDto educationEntityToDto(CandidateEducation candidateEducation);

    @IterableMapping(qualifiedByName = "toEducationDto")
    @Named("educationEntitiesToDtos")
    List<CandidateEducationGetDto> educationEntitiesToDtos(List<CandidateEducation> candidateEducations);

    ////

    @Mapping(target = "jobName", source = "jobPosition.jobName")
    @Mapping(target = "candidateCVId", source = "candidateCV.id")
    @Named("toExperienceDto")
    CandidateJobExperienceGetDto experinceEntityToDto(CandidateJobExperience candidateJobExperience);

    @IterableMapping(qualifiedByName = "toExperienceDto")
    @Named("experienceEntitiesToDtos")
    List<CandidateJobExperienceGetDto> experienceEntitiesToDtos(List<CandidateJobExperience> candidateJobExperiences);

    ////

    @Mapping(target = "candidateCVId", source = "candidateCV.id")
    @Mapping(target = "name", source = "language.name")
    @Named("toLanguageDto")
    CandidateLanguageGetDto map(CandidateLanguage candidateLanguage);

    @IterableMapping(qualifiedByName = "toLanguageDto")
    @Named("languageEntitiesToDtos")
    List<CandidateLanguageGetDto> languageEntitiesToDtos(List<CandidateLanguage> candidateLanguages);

    ///

    @Mapping(target = "candidateCVId", source = "candidateCV.id")
    @Named("toTechnologyDto")
    CandidateTechnologyGetDto map(CandidateTechnology candidateTechnology);

    @IterableMapping(qualifiedByName = "toTechnologyDto")
    @Named("technologyEntitiesToDtos")
    List<CandidateTechnologyGetDto> technologyEntitiesToDtos(List<CandidateTechnology> candidateTechnologies);

    @Mapping(target = "candidate.id", source = "candidateId")
    CandidateCV map(CandidateCVPostDto candidateCVPostDto);

    @Mapping(target = "candidateEducations", source = "candidateEducations", qualifiedByName = "educationEntitiesToDtos")
    @Mapping(target = "candidateJobExperiences", source = "candidateJobExperiences", qualifiedByName = "experienceEntitiesToDtos")
    @Mapping(target = "candidateLanguages", source = "candidateLanguages", qualifiedByName = "languageEntitiesToDtos")
    @Mapping(target = "candidateTechnologys", source = "candidateTechnologies", qualifiedByName = "technologyEntitiesToDtos")
    @Mapping(target = "candidateId", source = "candidate.id")
    @Named("toGetDto")
    CandidateCVGetDto map(CandidateCV candidateCV);

    @IterableMapping(qualifiedByName = "toGetDto")
    List<CandidateCVGetDto> map(List<CandidateCV> candidateCVS);

}
