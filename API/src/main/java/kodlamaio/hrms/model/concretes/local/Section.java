package kodlamaio.hrms.model.concretes.local;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sections")
@NoArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonIgnoreProperties("university")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "university_id")
    @JsonIgnoreProperties("city")
    private University university;
}
