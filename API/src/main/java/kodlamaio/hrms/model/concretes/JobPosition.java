package kodlamaio.hrms.model.concretes;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "job_positions")
public class JobPosition {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String jobName;
}
