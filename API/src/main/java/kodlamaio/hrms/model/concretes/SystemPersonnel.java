package kodlamaio.hrms.model.concretes;

import kodlamaio.hrms.core.entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_personnel")
public class SystemPersonnel extends User {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;
}
