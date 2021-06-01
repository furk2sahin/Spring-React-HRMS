package kodlamaio.hrms.model.dtos.abstracts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserGetDto {
    private UUID uuid;
    private Date creationDate;
    private String email;
}
