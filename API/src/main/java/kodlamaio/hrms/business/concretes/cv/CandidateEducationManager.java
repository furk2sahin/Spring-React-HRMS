package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.business.abstracts.cv.CandidateEducationService;
import kodlamaio.hrms.business.abstracts.local.SectionService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CandidateEducationManager implements CandidateEducationService {

    private final CandidateEducationDao candidateEducationDao;
    private final CandidateEducationMapper candidateEducationMapper;
    private final BusinessRuleService businessRuleService;
    private final SectionService sectionService;
    private final CandidateCvService candidateCvService;

    @Autowired
    public CandidateEducationManager(CandidateEducationDao candidateEducationDao,
                                     CandidateEducationMapper candidateEducationMapper,
                                     BusinessRuleService businessRuleService,
                                     SectionService sectionService,
                                     CandidateCvService candidateCvService) {
        this.candidateEducationDao = candidateEducationDao;
        this.candidateEducationMapper = candidateEducationMapper;
        this.businessRuleService = businessRuleService;
        this.sectionService = sectionService;
        this.candidateCvService = candidateCvService;
    }

    @Override
    public ResponseEntity<DataResult<CandidateEducationGetDto>> add(CandidateEducationPostDto candidateEducationPostDto)  {
        CandidateEducation candidateEducation = candidateEducationMapper.map(candidateEducationPostDto);
        Date startDate;
        Date endDate;
        DataResult<Date> dateDataResult;
        try {
            dateDataResult = businessRuleService.checkIfEndDateNull(candidateEducationPostDto.getEndDateString());
            endDate = dateDataResult.getData();
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(candidateEducationPostDto.getStartDateString());

        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("Parse Exception"));
        }

        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfBooleanValueTrue(
                        sectionService.existsById(candidateEducationPostDto.getSectionId()),
                        "No section found with given id."
                ),
                businessRuleService.checkIfBooleanValueTrue(
                        candidateCvService.existsById(candidateEducationPostDto.getCandidateCVId()),
                        "No cv found with given id."
                ),
                businessRuleService.checkDates(startDate, endDate),
                businessRuleService.checkIfBooleanValueTrue(
                        !existsByCandidateCVIdAndSectionIdAndCandidateEducationDegreeId(
                                candidateEducationPostDto.getCandidateCVId(),
                                candidateEducationPostDto.getSectionId(),
                                candidateEducationPostDto.getDegree()
                        ),
                        "This education already exists in your cv."
                )
        ));

        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
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

    @Override
    public boolean existsByCandidateCVIdAndSectionIdAndCandidateEducationDegreeId(Long candidateCvId,
                                                                                      Integer sectionId,
                                                                                      byte degreeId) {
        return candidateEducationDao
                .existsByCandidateCVIdAndSectionIdAndCandidateEducationDegreeId(candidateCvId, sectionId, degreeId);
    }
}
