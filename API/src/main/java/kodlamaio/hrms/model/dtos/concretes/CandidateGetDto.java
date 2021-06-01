package kodlamaio.hrms.model.dtos.concretes;

import kodlamaio.hrms.model.dtos.abstracts.UserGetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CandidateGetDto extends UserGetDto {
    private String email;
    private boolean enabled;
    private String name;
    private String surname;
    private String nationalIdentity;
    private Date birthDate;
}
