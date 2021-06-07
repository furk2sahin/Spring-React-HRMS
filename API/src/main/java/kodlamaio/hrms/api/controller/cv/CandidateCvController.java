package kodlamaio.hrms.api.controller.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateCVGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateCVPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/candidate-cv")
@CrossOrigin
public class CandidateCvController {

    private final CandidateCvService candidateCvService;

    @Autowired
    public CandidateCvController(CandidateCvService candidateCvService) {
        this.candidateCvService = candidateCvService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<DataResult<CandidateCVGetDto>> add(@RequestBody @Valid CandidateCVPostDto candidateCVPostDto){
        return candidateCvService.add(candidateCVPostDto);
    }

    @GetMapping( "/get-all")
    public ResponseEntity<DataResult<List<CandidateCVGetDto>>> getAll(){
        return candidateCvService.getAll();
    }

    @PostMapping("/update-photo")
    public ResponseEntity<Result> uploadPhoto(@RequestParam("image") MultipartFile file,
                                              @RequestParam("cvId") Long cvId){
        return candidateCvService.updatePhoto(file, cvId);
    }

    @GetMapping("/get-by-candidate-id/{id}")
    public ResponseEntity<DataResult<List<CandidateCVGetDto>>> findAllByCandidateId(@PathVariable("id") Long id){
        return candidateCvService.findAllByCandidateId(id);
    }
}
