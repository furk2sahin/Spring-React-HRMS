package kodlamaio.hrms.model.dtos.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateJobExperiencePostDto {

    @Size(min = 2, max = 100)
    private String companyName;

    @NotNull
    @Pattern(regexp = "^(19|20)\\d\\d(-)(0[1-9]|1[012])(-)(0[1-9]|[12][0-9]|3[01])$")
    private String startDateString;

    private String endDateString;

    @Min(1)
    @NotNull
    private Long candidateCVId;

    @Min(1)
    @NotNull
    private Long jobPositionId;


}
