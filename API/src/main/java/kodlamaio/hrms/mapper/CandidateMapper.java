package kodlamaio.hrms.mapper;

import kodlamaio.hrms.model.concretes.Candidate;
import kodlamaio.hrms.model.dtos.concretes.CandidatePostDto;
import kodlamaio.hrms.model.dtos.concretes.CandidateGetDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Named("candidatePostDtoToCandidate")
    Candidate candidatePostDtoToCandidate(CandidatePostDto candidatePostDto);

    @Named("candidateToCandidateGetDto")
    CandidateGetDto candidateToCandidateGetDto(Candidate candidate);

    @IterableMapping(qualifiedByName = "candidateToCandidateGetDto")
    List<CandidateGetDto> candidatesToCandidateGetDtos(List<Candidate> candidates);
}
