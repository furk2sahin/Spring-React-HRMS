package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.adapter.abstracts.FileService;
import kodlamaio.hrms.core.adapter.abstracts.ImageCheckerService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.mapper.CandidateCVMapper;
import kodlamaio.hrms.mapper.CandidateMapper;
import kodlamaio.hrms.model.concretes.cv.CandidateCV;
import kodlamaio.hrms.model.dtos.concretes.cv.*;
import kodlamaio.hrms.repositories.cv.CandidateCvDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CandidateCvManager implements CandidateCvService {

    private final CandidateCvDao candidateCvDao;
    private final CandidateService candidateService;
    private final FileService fileService;
    private final BusinessRuleService businessRuleService;
    private final CandidateCVMapper candidateCvMapper;
    private final ImageCheckerService imageCheckerService;

    @Autowired
    public CandidateCvManager(CandidateCvDao candidateCvDao,
                              CandidateService candidateService,
                              FileService fileService,
                              BusinessRuleService businessRuleService,
                              CandidateCVMapper candidateCvMapper,
                              ImageCheckerService imageCheckerService) {
        this.candidateCvDao = candidateCvDao;
        this.candidateService = candidateService;
        this.fileService = fileService;
        this.businessRuleService = businessRuleService;
        this.candidateCvMapper = candidateCvMapper;
        this.imageCheckerService = imageCheckerService;
    }

    @Override
    public ResponseEntity<DataResult<CandidateCVGetDto>> add(CandidateCVPostDto candidateCVPostDto) {
        if(candidateService.existsById(candidateCVPostDto.getCandidateId())){
            CandidateCV candidateCV = candidateCvMapper.map(candidateCVPostDto);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    candidateCvMapper.map(candidateCvDao.save(candidateCV)),
                    "Data added successfully."
            ));
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult("No candidate with given id."));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateCVGetDto>>> getAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(
                candidateCvMapper.map(candidateCvDao.findAllByActiveTrue()))
        );
    }

    @Override
    public ResponseEntity<Result> updatePhoto(MultipartFile file, Long id) {
        Result result = businessRuleService.checkIfFileIsAnImage(file.getOriginalFilename());
        if(result.isSuccess()){

            CandidateCV candidateCV = candidateCvDao.findById(id).orElse(null);
            if(candidateCV != null){

                if(candidateCV.isActive()){
                    if(imageCheckerService.detectPersonCount(file) != 1){
                        return ResponseEntity.badRequest().body(new ErrorResult("There should be only 1 face in image."));

                    }else if(imageCheckerService.detectModerationLabels(file) != 0){
                        return ResponseEntity.badRequest().body(new ErrorResult("Moderation labels detected! " +
                                "Please use another image!"));
                    }
                    if(candidateCV.getPhotoPath() != null)
                        fileService.deleteFile(candidateCV.getPhotoPath());

                    String fileName = fileService.uploadFile(file);
                    candidateCV.setPhotoPath(fileName);
                    candidateCvDao.save(candidateCV);
                    return ResponseEntity.ok(new SuccessResult("Photo uploaded successfully!"));

                } else
                    return ResponseEntity.badRequest().body(new ErrorResult("This candidate CV is inactive."));
            }
            return ResponseEntity.badRequest().body(new ErrorResult("No cv found with given id"));
        }
        else {
            return ResponseEntity.badRequest().body(new ErrorResult("Error when updating photo!"));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateCVGetDto>>> findAllByCandidateId(Long id) {
        List<CandidateCV> candidateCVS = candidateCvDao.findAllByCandidateIdAndActiveTrue(id);
        if (candidateCVS.isEmpty()) {
            return ResponseEntity.ok(new ErrorDataResult<>("No cv found with given id"));
        }
        List<CandidateCVGetDto> candidateCVGetDtos = candidateCvMapper.map(candidateCVS);
        for(int i = 0; i < candidateCVGetDtos.size(); i++){
            String photoPath = candidateCVS.get(i).getPhotoPath();
            if(photoPath != null)
                candidateCVGetDtos.get(i).setBase64Photo(fileService.downloadFile(
                        photoPath
                ));
        }
        return ResponseEntity.ok(new SuccessDataResult<>(candidateCVGetDtos, "Data listed successfully!"));
    }

    @Override
    public boolean existsById(Long id) {
        return candidateCvDao.existsById(id);
    }

    @Override
    public byte[] existsById2(Long id) {

        return null;
    }
}
