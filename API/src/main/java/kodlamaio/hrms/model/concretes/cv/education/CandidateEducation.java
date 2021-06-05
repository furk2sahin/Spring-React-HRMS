package kodlamaio.hrms.model.concretes.cv.education;

import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import kodlamaio.hrms.model.concretes.local.Faculty;
import kodlamaio.hrms.model.concretes.local.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Past;
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

    @OneToOne
    @JoinColumn(name = "section_id")
    private Section section;
}
