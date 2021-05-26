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
    @Size(min = 10, max = 10, message = "Phone number length should be 10. For example 53xxxxxxxx.")
    @Pattern(regexp = "(^[1-9][0-9]*$)|(^\\d{10}$)", message = "Phone can't start with 0 or contain text.")
    private String phone;

    @OneToOne(mappedBy = "employer")
    @JsonIgnore
    private EmployerVerify employerVerify;
}
