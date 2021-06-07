package kodlamaio.hrms.model.dtos.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisePostDto {
    @Min(1)
    private int maxSalary;

    @Min(1)
    private int minSalary;

    @Min(1)
    private int openPositionCount;
    private String jobDefinition;

    @Min(1)
    @NotNull
    private Long cityId;

    @Min(1)
    @NotNull
    private Long employerId;

    @Min(1)
    @NotNull
    private Long jobPositionId;
}
