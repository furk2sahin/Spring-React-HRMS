package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.business.abstracts.cv.CandidateJobExperienceService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.mapper.CandidateJobExperienceMapper;
import kodlamaio.hrms.model.concretes.cv.CandidateJobExperience;
import kodlamaio.hrms.model.concretes.cv.education.CandidateEducation;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateJobExperienceGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateJobExperiencePostDto;
import kodlamaio.hrms.repositories.cv.CandidateJobExperienceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CandidateJobExperienceManager implements CandidateJobExperienceService {

    private final CandidateJobExperienceDao candidateJobExperienceDao;
    private final CandidateJobExperienceMapper candidateJobExperienceMapper;
    private final BusinessRuleService businessRuleService;
    private final CandidateCvService candidateCvService;
    private final JobPositionService jobPositionService;


    @Autowired
    public CandidateJobExperienceManager(CandidateJobExperienceDao candidateJobExperienceDao,
                                         CandidateJobExperienceMapper candidateJobExperienceMapper,
                                         BusinessRuleService businessRuleService,
                                         CandidateCvService candidateCvService,
                                         JobPositionService jobPositionService) {
        this.candidateJobExperienceDao = candidateJobExperienceDao;
        this.candidateJobExperienceMapper = candidateJobExperienceMapper;
        this.businessRuleService = businessRuleService;
        this.candidateCvService = candidateCvService;
        this.jobPositionService = jobPositionService;
    }

    @Override
    public ResponseEntity<DataResult<CandidateJobExperienceGetDto>> add(CandidateJobExperiencePostDto candidateJobExperiencePostDto) {
        CandidateJobExperience candidateJobExperience = candidateJobExperienceMapper.map(candidateJobExperiencePostDto);
        Date startDate;
        Date endDate;
        DataResult<Date> dateDataResult;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(candidateJobExperiencePostDto.getStartDateString());
            dateDataResult = businessRuleService.checkIfEndDateNull(candidateJobExperiencePostDto.getEndDateString());
            endDate = dateDataResult.getData();
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("Parse Exception"));
        }

        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfBooleanValueTrue(
                        jobPositionService.existsById(candidateJobExperiencePostDto.getJobPositionId()),
                        "No cv position found with given id"
                ),
                businessRuleService.checkIfBooleanValueTrue(
                        candidateCvService.existsById(candidateJobExperiencePostDto.getCandidateCVId()),
                        "No cv position found with given id"
                ),
                businessRuleService.checkDates(startDate, endDate),
                dateDataResult,
                businessRuleService.checkIfBooleanValueTrue(
                        !existsByCandidateCVIdAndAndCompanyNameAndJobPositionId(
                                candidateJobExperiencePostDto.getCandidateCVId(),
                                candidateJobExperiencePostDto.getCompanyName(),
                                candidateJobExperiencePostDto.getJobPositionId()
                        ),
                        "This Job Experience already exists in your cv"
                )
        ));

        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        } else {
            candidateJobExperience.setEndDate(endDate);
            candidateJobExperience.setStartDate(startDate);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    candidateJobExperienceMapper.map(candidateJobExperienceDao.save(candidateJobExperience))
            ));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateJobExperienceGetDto>>> getAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(
                candidateJobExperienceMapper.map(candidateJobExperienceDao.findAll())
        ));
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateJobExperienceGetDto>>> findAllByCandidateCVIdOrderByEndDateDesc(Long cvId) {
        return ResponseEntity.ok(new SuccessDataResult<>(
                candidateJobExperienceMapper.map(candidateJobExperienceDao.findAllByCandidateCVIdOrderByEndDateDesc(cvId)),
                "Data listed by end date desc"
        ));
    }

    @Override
    public boolean existsByCandidateCVIdAndAndCompanyNameAndJobPositionId(Long candidateCVId, String companyName, Long jobPositionId) {
        return existsByCandidateCVIdAndAndCompanyNameAndJobPositionId(candidateCVId, companyName, jobPositionId);
    }
}
