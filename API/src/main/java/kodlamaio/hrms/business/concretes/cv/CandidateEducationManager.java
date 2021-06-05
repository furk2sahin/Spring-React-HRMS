package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateEducationService;
import kodlamaio.hrms.business.abstracts.local.SectionService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.mapper.CandidateEducationMapper;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateEducationGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateEducationPostDto;
import kodlamaio.hrms.repositories.cv.CandidateEducationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CandidateEducationManager implements CandidateEducationService {

    private final CandidateEducationDao candidateEducationDao;
    private final CandidateEducationMapper candidateEducationMapper;
    private final BusinessRuleService businessRuleService;
    private final SectionService sectionService;

    @Autowired
    public CandidateEducationManager(CandidateEducationDao candidateEducationDao,
                                     CandidateEducationMapper candidateEducationMapper,
                                     BusinessRuleService businessRuleService,
                                     SectionService sectionService) {
        this.candidateEducationDao = candidateEducationDao;
        this.candidateEducationMapper = candidateEducationMapper;
        this.businessRuleService = businessRuleService;
        this.sectionService = sectionService;
    }

    @Override
    public ResponseEntity<DataResult<CandidateEducationGetDto>> add(CandidateEducationPostDto candidateEducationPostDto) {
        CandidateEducation candidateEducation = candidateEducationMapper.map(candidateEducationPostDto);
        Date startDate;
        Date endDate;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(candidateEducationPostDto.getStartDateString());
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(candidateEducationPostDto.getEndDateString());
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("Parse Exception"));
        }
        Result result = businessRuleService.checkDates(startDate, endDate);

        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        } else if(!sectionService.existsById(candidateEducationPostDto.getSectionId())){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("No section found with given id."));
        } else {
            candidateEducation.setEndDate(endDate);
            candidateEducation.setStartDate(startDate);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    candidateEducationMapper.map(candidateEducationDao.save(candidateEducation))
            ));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateEducationGetDto>>> findAllByCandidateCVIdOrderByEndDateDesc(Long cvId) {
        return ResponseEntity.ok(new SuccessDataResult<>(
                candidateEducationMapper.map(candidateEducationDao.findAllByCandidateCVIdOrderByEndDateDesc(cvId)),
                "Data listed by end date desc"
        ));
    }
}
