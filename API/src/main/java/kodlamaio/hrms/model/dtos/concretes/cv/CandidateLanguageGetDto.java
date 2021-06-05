package kodlamaio.hrms.model.dtos.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateLanguageGetDto {
    
    private Long id;
    
    private byte level;

    private String name;

    private Date createDate;
    
    private Long candidateCVId;
}
