package kodlamaio.hrms.model.dtos.concretes.cv;

import kodlamaio.hrms.model.concretes.local.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEducationGetDto {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private String degreeName;
    private String gradeName;
    private Section section;
    // private CandidateCVDto candidateCvDto;
}
