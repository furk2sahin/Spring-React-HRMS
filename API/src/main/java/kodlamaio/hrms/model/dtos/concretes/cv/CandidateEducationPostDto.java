package kodlamaio.hrms.model.dtos.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEducationPostDto {

    @NotNull
    @Pattern(regexp = "^(19|20)\\d\\d(-)(0[1-9]|1[012])(-)(0[1-9]|[12][0-9]|3[01])$")
    private String startDateString;

    @NotNull
    @Pattern(regexp = "^(19|20)\\d\\d(-)(0[1-9]|1[012])(-)(0[1-9]|[12][0-9]|3[01])$")
    private String endDateString;

    @Min(1)
    @NotNull
    private Long candidateCVId;

    @Min(1)
    @Max(5)
    private byte degree;

    @Min(1)
    @Max(7)
    private byte grade;

    @Min(400)
    @NotNull
    private Integer sectionId;
}
