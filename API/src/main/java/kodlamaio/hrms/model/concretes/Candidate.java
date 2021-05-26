package kodlamaio.hrms.model.concretes;

import kodlamaio.hrms.model.abstracts.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;


@Data
@Entity
@Table(name = "candidates")
@EqualsAndHashCode(callSuper = true)
public class Candidate extends User {

    @NotBlank(message = "Name cannot be empty.")
    @Column(nullable = false)
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50.")
    private String name;

    @NotBlank(message = "Surname cannot be empty.")
    @Column(nullable = false)
    @Size(min = 3, max = 50, message = "Surname length should be between 3 and 50.")
    private String surname;

    @NotBlank(message = "National identity cannot be empty.")
    @Column(nullable = false, length = 11, unique = true)
    @Size(min = 11, max = 11, message = "National identity length must be 11.")
    @Pattern(regexp = "(^[1-9][0-9]*$)|(^\\d{10}$)", message = "National identity can't start with 0 or contain text.")
    private String nationalIdentity;

    @Column(nullable = false)
    private Date birthDate;
}
