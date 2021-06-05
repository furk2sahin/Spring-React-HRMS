package kodlamaio.hrms.model.dtos.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateJobExperienceGetDto {

    private Long id;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private String companyName;
    private Long candidateCVId;
    private String jobName;
}
