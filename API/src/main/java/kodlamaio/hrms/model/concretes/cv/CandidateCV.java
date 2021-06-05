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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @Pattern(regexp = "^(github\\.com)/[a-zA-z0-9]{2}[a-zA-z0-9]*")
    @NotBlank
    @Size(max = 150)
    private String githubLink;

    @Pattern(regexp = "^(www\\.linkedin\\.com/in)/[a-zA-z0-9-/]{10}[a-zA-z0-9-/]*")
    @Size(max = 150)
    @NotBlank
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