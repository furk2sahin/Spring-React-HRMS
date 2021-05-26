package kodlamaio.hrms.model.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kodlamaio.hrms.model.concretes.VerificationCode;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, unique = true)
    private UUID uuid = UUID.randomUUID();

    @CreationTimestamp
    @Column(updatable = false)
    private Date creationDate;

    @Email(message = "Wrong email format")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Column(nullable = false)
    @Size(min = 6, max = 30, message = "Password length should be between 6-30.")
    private String password;

    @Transient
    @NotBlank(message = "Password check cannot be empty.")
    @Column(nullable = false)
    @Size(min = 6, max = 30, message = "Password length should be between 6-30.")
    private String passwordCheck;

    private boolean enabled = true;
}
