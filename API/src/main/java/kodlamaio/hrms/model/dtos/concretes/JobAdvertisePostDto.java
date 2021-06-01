package kodlamaio.hrms.model.dtos.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisePostDto {
    private int maxSalary;
    private int minSalary;
    private int openPositionCount;
    private String jobDefinition;
    private Long cityId;
    private Long employerId;
    private Long jobPositionId;
}
