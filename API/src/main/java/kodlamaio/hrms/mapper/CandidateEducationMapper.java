package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateEducationGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateEducationPostDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateEducationMapper {


    @Mapping(source = "candidateCVId", target = "candidateCV.id")
    @Mapping(source = "degree", target = "candidateEducationDegree.id")
    @Mapping(source = "grade", target = "candidateEducationGrade.id")
    @Mapping(source = "sectionId", target = "section.id")
    CandidateEducation map(CandidateEducationPostDto candidateEducationPostDto);

    @Named("toGetDto")
    @Mapping(source = "candidateEducationDegree.degree", target = "degreeName")
    @Mapping(source = "candidateEducationGrade.grade", target = "gradeName")
    @Mapping(source = "candidateCV.id", target = "candidateCVId")
    CandidateEducationGetDto map(CandidateEducation candidateEducation);

    @IterableMapping(qualifiedByName = "toGetDto")
    List<CandidateEducationGetDto> map(List<CandidateEducation> candidateEducations);
}
