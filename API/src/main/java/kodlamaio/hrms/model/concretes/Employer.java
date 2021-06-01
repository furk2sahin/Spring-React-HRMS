package kodlamaio.hrms.model.concretes;

import kodlamaio.hrms.core.entities.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "employers")
@EqualsAndHashCode(callSuper = true)
public class Employer extends User {

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String webAddress;

    @Column(nullable = false)
    private String phone;
}
