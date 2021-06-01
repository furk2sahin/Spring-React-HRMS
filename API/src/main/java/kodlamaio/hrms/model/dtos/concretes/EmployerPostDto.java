package kodlamaio.hrms.model.dtos.concretes;

import kodlamaio.hrms.model.dtos.abstracts.UserPostDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployerPostDto extends UserPostDto {

    @NotBlank(message = "Company name cannot be empty.")
    @Size(min = 2, max = 200, message = "Company name length should be between 3-200")
    private String companyName;

    @NotBlank(message = "Company web address cannot be empty.")
    @Pattern(regexp = "[a-zA-Z0-9]?[a-zA-Z0-9-]+[a-zA-Z0-9]\\.[a-zA-Z0-9]+",
            message = "Wrong web address format. Should be like google.com")
    private String webAddress;

    @NotBlank(message = "Phone number cannot be empty.")
    @Size(min = 11, max = 14,
            message = "Wrong phone number format. Examples 0(2 or 5)xx xxx xx xx or 0(2 or 5)xxxxxxxxx")
    @Pattern(regexp = "^(0[25])([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",
            message = "Wrong phone number format. Examples 0(2 or 5)xx xxx xx xx or 0(2 or 5)xxxxxxxxx")
    private String phone;
}
