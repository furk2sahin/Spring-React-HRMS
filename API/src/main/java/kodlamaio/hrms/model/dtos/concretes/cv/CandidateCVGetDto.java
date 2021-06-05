package kodlamaio.hrms.model.dtos.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateCVGetDto {

    private Long id;

    private String githubLink;

    private String linkedLink;

    private byte[] base64Photo;

    private String description;

    private Long candidateId;

    private Date createDate;

    private Date updateDate;

    private boolean active;

    private List<CandidateEducationGetDto> candidateEducations = new ArrayList<>();

    private List<CandidateJobExperienceGetDto> candidateJobExperiences = new ArrayList<>();

    private List<CandidateLanguageGetDto> candidateLanguages = new ArrayList<>();

    private List<CandidateTechnologyGetDto> candidateTechnologys = new ArrayList<>();
}
