package kodlamaio.hrms.model.dtos.concretes;

import kodlamaio.hrms.model.dtos.abstracts.UserGetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployerGetDto extends UserGetDto {
    private String companyName;
    private String webAddress;
    private String phone;
}
