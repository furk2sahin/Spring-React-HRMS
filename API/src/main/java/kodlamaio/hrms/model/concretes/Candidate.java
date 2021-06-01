package kodlamaio.hrms.model.concretes;

import kodlamaio.hrms.core.entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "candidates")
@EqualsAndHashCode(callSuper = true)
public class Candidate extends User {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, length = 11, unique = true)
    private String nationalIdentity;

    @Column(nullable = false)
    private Date birthDate;
}
