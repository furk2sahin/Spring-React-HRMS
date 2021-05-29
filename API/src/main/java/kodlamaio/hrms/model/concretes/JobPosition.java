package kodlamaio.hrms.model.concretes;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Entity
@Table(name = "job_positions")
public class JobPosition {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Job name cannot be empty.")
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 255, message = "Job name length should between 3-255.")
    private String jobName;
}
