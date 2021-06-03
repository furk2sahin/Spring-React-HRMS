package kodlamaio.hrms.model.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "candidate_technology")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String technologyName;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "candidate_cv_id")
    private CandidateCV candidateCV;
}
