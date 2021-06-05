package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateJobExperienceGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateJobExperiencePostDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateJobExperienceMapper {

    @Mapping(target = "candidateCV.id", source = "candidateCVId")
    @Mapping(target = "jobPosition.id", source = "jobPositionId")
    CandidateJobExperience map(CandidateJobExperiencePostDto candidateJobExperiencePostDto);

    @Mapping(target = "jobName", source = "jobPosition.jobName")
    @Mapping(target = "candidateCVId", source = "candidateCV.id")
    @Named("toGetDto")
    CandidateJobExperienceGetDto map(CandidateJobExperience candidateJobExperience);

    @IterableMapping(qualifiedByName = "toGetDto")
    List<CandidateJobExperienceGetDto> map(List<CandidateJobExperience> candidateJobExperiences);

}
