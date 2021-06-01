package kodlamaio.hrms.model.concretes;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "job_advertises")
public class JobAdvertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int maxSalary;

    @Column(nullable = false)
    private int minSalary;

    @Column(nullable = false)
    private int openPositionCount;

    @Column(name = "job_definition", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @Lob
    @NotBlank(message = "Job Definition cannot be empty.")
    private String jobDefinition;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobPosition jobPosition;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;
}