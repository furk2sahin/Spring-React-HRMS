package kodlamaio.hrms.model.concretes.local;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "faculties")
@NoArgsConstructor
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "university_id")
    @JsonIgnoreProperties({"faculties", "sections"})
    private University university;
}
