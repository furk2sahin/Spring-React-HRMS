package kodlamaio.hrms.model.concretes.cv;

import kodlamaio.hrms.model.concretes.Candidate;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String name;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidateCV")
    private List<CandidateEducation> candidateEducations;
}
