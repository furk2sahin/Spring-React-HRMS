package kodlamaio.hrms.model.concretes;

import kodlamaio.hrms.model.abstracts.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "verification_codes")
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private boolean confirmed;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private Date confirmDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
