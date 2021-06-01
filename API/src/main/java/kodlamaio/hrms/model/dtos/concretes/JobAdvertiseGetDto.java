package kodlamaio.hrms.model.dtos.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertiseGetDto {
    private int maxSalary;
    private int minSalary;
    private int openPositionCount;
    private String jobDefinition;
    private CityGetDto city;
    private EmployerGetDto employer;
    private JobPositionDto jobPosition;
}