package kodlamaio.hrms.model.dtos.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateTechnologyPostDto {

    @Size(min = 1, max = 100)
    @NotNull
    private String technologyName;

    @Min(1)
    @NotNull
    private Long candidateCVId;
}
