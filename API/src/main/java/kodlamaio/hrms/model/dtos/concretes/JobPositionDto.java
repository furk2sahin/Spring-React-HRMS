package kodlamaio.hrms.model.dtos.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPositionDto {

    @NotBlank(message = "Job name cannot be empty.")
    @Size(min = 3, max = 255, message = "Job name length should between 3-255.")
    private String jobName;
}
