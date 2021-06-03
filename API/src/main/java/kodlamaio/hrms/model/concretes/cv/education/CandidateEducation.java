package kodlamaio.hrms.model.concretes.cv.education;

import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "candidate_educations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String school;

    private String department;

    private Date startDate;

    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "candidate_cv_id")
    private CandidateCV candidateCV;

    @ManyToOne
    @JoinColumn(name = "education_degree")
    private CandidateEducationDegree candidateEducationDegree;

    @ManyToOne
    @JoinColumn(name = "education_grade")
    private CandidateEducationGrade candidateEducationGrade;

}
