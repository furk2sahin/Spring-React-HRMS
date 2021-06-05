package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.adapter.abstracts.FileService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import kodlamaio.hrms.model.concretes.cv.CandidateLanguage;
import kodlamaio.hrms.model.concretes.cv.CandidateTechnology;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import kodlamaio.hrms.repositories.cv.CandidateCvDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CandidateCvManager implements CandidateCvService {

    private final CandidateCvDao candidateCvDao;
    private final FileService fileService;
    private final BusinessRuleService businessRuleService;

    @Autowired
    public CandidateCvManager(CandidateCvDao candidateCvDao,
                              FileService fileService,
                              BusinessRuleService businessRuleService) {
        this.candidateCvDao = candidateCvDao;
        this.fileService = fileService;
        this.businessRuleService = businessRuleService;
    }

    @Override
    public ResponseEntity<DataResult<CandidateCV>> add(CandidateCV candidateCV, MultipartFile file) {

        Result result = businessRuleService.checkIfFileIsAnImage(file.getOriginalFilename());

        if(result.isSuccess()){
            List<CandidateEducation> candidateEducations = candidateCV.getCandidateEducations();
            List<CandidateJobExperience> candidateJobExperiences = candidateCV.getCandidateJobExperiences();
            List<CandidateLanguage> candidateLanguages = candidateCV.getCandidateLanguages();
            List<CandidateTechnology> candidateTechnologies = candidateCV.getCandidateTechnologies();

            if(candidateEducations != null)
                candidateEducations
                        .forEach(candidateEducation -> candidateEducation.setCandidateCV(candidateCV));
            if(candidateJobExperiences != null)
                candidateJobExperiences
                    .forEach(candidateJobExperience -> candidateJobExperience.setCandidateCV(candidateCV));
            if(candidateLanguages != null)
                candidateLanguages
                    .forEach(candidateLanguage -> candidateLanguage.setCandidateCV(candidateCV));
            if(candidateTechnologies != null)
                candidateTechnologies
                    .forEach(candidateTechnology -> candidateTechnology.setCandidateCV(candidateCV));

            String fileName = fileService.uploadFile(file);
            candidateCV.setPhotoPath(fileName);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    candidateCvDao.save(candidateCV),
                    "Data added successfully."
            ));
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult(result.getMessage()));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateCV>>> getAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(candidateCvDao.findAllByActiveTrue()));
    }

    @Override
    public ResponseEntity<Result> updatePhoto(MultipartFile file, Long id) {
        CandidateCV candidateCV = candidateCvDao.findById(id).orElse(null);
        if(candidateCV != null){
            if(candidateCV.isActive()){
                fileService.deleteFile(candidateCV.getPhotoPath());
                String fileName = fileService.uploadFile(file);
                candidateCV.setPhotoPath(fileName);
                candidateCvDao.save(candidateCV);
                return ResponseEntity.ok(new SuccessResult("Photo uploaded successfully!"));
            } else return ResponseEntity.badRequest().body(new ErrorResult("This candidate CV is inactive."));
        }else {
            return ResponseEntity.badRequest().body(new ErrorResult("Error when updating photo!"));
        }
    }


}
