package kodlamaio.hrms.model.concretes;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "employers_to_verify")
public class EmployerVerify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean verified;

    @UpdateTimestamp
    @Column(insertable = false)
    private Date confirmDate;

    @OneToOne
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "system_personnel_id", referencedColumnName = "id")
    private SystemPersonnel systemPersonnel;
}
