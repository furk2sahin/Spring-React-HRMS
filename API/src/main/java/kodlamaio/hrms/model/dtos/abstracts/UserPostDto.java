package kodlamaio.hrms.model.dtos.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserPostDto {

    @Email(message = "Wrong email format")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 6, max = 30, message = "Password length should be between 6-30.")
    private String password;

    @Transient
    @NotBlank(message = "Password check cannot be empty.")
    @Size(min = 6, max = 30, message = "Password length should be between 6-30.")
    private String passwordCheck;
}
