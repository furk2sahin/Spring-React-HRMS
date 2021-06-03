package kodlamaio.hrms.model.concretes.cv.education;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "candidate_education_grade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEducationGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    private String grade;
}
