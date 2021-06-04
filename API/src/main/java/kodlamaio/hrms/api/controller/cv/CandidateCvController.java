package kodlamaio.hrms.api.controller.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidate-cv")
public class CandidateCvController {

    private final CandidateCvService candidateCvService;

    @Autowired
    public CandidateCvController(CandidateCvService candidateCvService) {
        this.candidateCvService = candidateCvService;
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CandidateCV>> add(@RequestPart("image") MultipartFile image,
                                                       @RequestPart("cv") CandidateCV candidateCV){
        return candidateCvService.add(candidateCV, image);
    }

    @GetMapping( "/get-all")
    public ResponseEntity<DataResult<List<CandidateCV>>> getAll(){
        return candidateCvService.getAll();
    }

    @PostMapping("/update-photo")
    public ResponseEntity<Result> uploadPhoto(@RequestParam("image") MultipartFile file,
                                              @RequestParam("cvId") Long cvId){
        return candidateCvService.updatePhoto(file, cvId);
    }

    @PostMapping("/update-cv")
    public ResponseEntity<DataResult<CandidateCV>> update(){
        return null;
    }
}
