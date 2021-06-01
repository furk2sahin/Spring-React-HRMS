package kodlamaio.hrms.model.dtos.concretes;

import kodlamaio.hrms.model.dtos.abstracts.UserPostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CandidatePostDto extends UserPostDto {

    private Date birthDate;

    @NotBlank(message = "Name cannot be empty.")
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50.")
    private String name;

    @NotBlank(message = "Surname cannot be empty.")
    @Size(min = 3, max = 50, message = "Surname length should be between 3 and 50.")
    private String surname;

    @NotBlank(message = "National identity cannot be empty.")
    @Size(min = 11, max = 11, message = "National identity length must be 11.")
    @Pattern(regexp = "^[1-9][0-9]{9}[02468]$",
            message = "National identity can't start with 0 or contain text.")
    private String nationalIdentity;
}
