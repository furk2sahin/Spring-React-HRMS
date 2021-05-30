package kodlamaio.hrms.model.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kodlamaio.hrms.model.abstracts.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "employers")
@EqualsAndHashCode(callSuper = true)
public class Employer extends User {

    @NotBlank(message = "Company name cannot be empty.")
    @Column(nullable = false)
    @Size(min = 2, max = 200, message = "Company name length should be between 3-200")
    private String companyName;

    @NotBlank(message = "Company web address cannot be empty.")
    @Pattern(regexp = "[a-zA-Z0-9]?[a-zA-Z0-9-]+[a-zA-Z0-9]\\.[a-zA-Z0-9]+",
            message = "Wrong web address format. Should be like google.com")
    @Column(nullable = false)
    private String webAddress;

    @NotBlank(message = "Phone number cannot be empty.")
    @Column(nullable = false)
    @Size(min = 11, max = 14,
            message = "Wrong phone number format. Examples 0(2 or 5)xx xxx xx xx or 0(2 or 5)xxxxxxxxx")
    @Pattern(regexp = "^(0[25])([0-9]{2})\\s?([0-9]{3})\\s?([0-9]{2})\\s?([0-9]{2})$",
            message = "Wrong phone number format. Examples 0(2 or 5)xx xxx xx xx or 0(2 or 5)xxxxxxxxx")
    private String phone;

    @OneToOne(mappedBy = "employer")
    @JsonIgnore
    private EmployerVerify employerVerify;
}
