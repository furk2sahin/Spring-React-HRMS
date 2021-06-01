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
public class SystemPersonnelGetDto extends UserGetDto {
    private String name;
    private String surname;
}
