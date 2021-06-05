package kodlamaio.hrms.model.dtos.concretes.cv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateCVPostDto {

    @Pattern(regexp = "^(github\\.com)/[a-zA-z0-9]{2}[a-zA-z0-9]*")
    @NotBlank
    @Size(max = 150)
    private String githubLink;

    @Pattern(regexp = "^(www\\.linkedin\\.com/in)/[a-zA-z0-9-/]{10}[a-zA-z0-9-/]*")
    @Size(max = 150)
    @NotBlank
    private String linkedLink;

    private String description;

    @Min(1)
    @NotNull
    private Long candidateId;
}
