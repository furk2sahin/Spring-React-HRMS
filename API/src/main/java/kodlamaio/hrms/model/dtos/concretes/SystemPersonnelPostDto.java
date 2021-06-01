package kodlamaio.hrms.model.dtos.concretes;

import kodlamaio.hrms.model.dtos.abstracts.UserPostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SystemPersonnelPostDto extends UserPostDto {

    @NotBlank(message = "Name cannot be empty.")
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50.")
    private String name;

    @NotBlank(message = "Surname cannot be empty.")
    @Size(min = 3, max = 50, message = "Surname length should be between 3 and 50.")
    private String surname;
}
