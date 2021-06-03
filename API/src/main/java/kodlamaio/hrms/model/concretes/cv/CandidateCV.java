package kodlamaio.hrms.model.concretes.cv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kodlamaio.hrms.model.concretes.Candidate;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidate_cvs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateCV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String githubLink;

    private String linkedLink;

    private String photoPath;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date updateDate;

    @Column(name = "is_active")
    private boolean active = true;

    private String description;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @JsonIgnoreProperties("candidateCVs")
    private Candidate candidate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidateCV")
    @JsonIgnoreProperties("candidateCV")
    private List<CandidateEducation> candidateEducations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidateCV")
    @JsonIgnoreProperties("candidateCV")
    private List<CandidateJobExperience> candidateJobExperiences;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidateCV")
    @JsonIgnoreProperties("candidateCV")
    private List<CandidateLanguage> candidateLanguages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidateCV")
    @JsonIgnoreProperties("candidateCV")
    private List<CandidateTechnology> candidateTechnologies;
}
