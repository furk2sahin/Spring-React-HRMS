package kodlamaio.hrms.model.dtos.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateLanguagePostDto {

    @Min(1)
    @NotNull
    private Short languageId;

    @Min(1)
    @Max(5)
    private byte level;

    @NotNull
    @Min(1)
    private Long candidateCVId;
}
