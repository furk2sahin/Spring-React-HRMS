package kodlamaio.hrms.model.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kodlamaio.hrms.model.abstracts.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_personnel")
public class SystemPersonnel extends User {

    @NotBlank(message = "Name cannot be empty.")
    @Column(nullable = false)
    @Size(min = 3, max = 50, message = "Name length should be between 3 and 50.")
    private String name;

    @NotBlank(message = "Surname cannot be empty.")
    @Column(nullable = false)
    @Size(min = 3, max = 50, message = "Surname length should be between 3 and 50.")
    private String surname;

    @OneToMany(mappedBy = "systemPersonnel")
    @JsonIgnore
    private List<EmployerVerify> employerVerifies;

}
