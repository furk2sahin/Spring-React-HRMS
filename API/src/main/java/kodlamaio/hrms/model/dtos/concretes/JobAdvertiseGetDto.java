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
    private CityGetDto cityGetDto;
    private EmployerGetDto employerGetDto;
    private JobPositionDto jobPositionDto;
}