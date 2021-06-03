package kodlamaio.hrms.model.concretes.cv.education;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "candidate_education_degree")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEducationDegree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    private String degree;
}
