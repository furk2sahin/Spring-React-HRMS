package kodlamaio.hrms.model.dtos.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateTechnologyGetDto {
    private Long id;

    private String technologyName;

    private Date createDate;

    private Long candidateCVId;
}
