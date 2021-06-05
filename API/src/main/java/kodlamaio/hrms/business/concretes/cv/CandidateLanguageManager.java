package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.business.abstracts.cv.CandidateLanguageService;
import kodlamaio.hrms.business.abstracts.local.LanguageService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.mapper.CandidateLanguageMapper;
import kodlamaio.hrms.model.concretes.cv.CandidateLanguage;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateLanguageGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateLanguagePostDto;
import kodlamaio.hrms.repositories.cv.CandidateLanguageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CandidateLanguageManager implements CandidateLanguageService {

    private final CandidateLanguageDao candidateLanguageDao;
    private final CandidateLanguageMapper candidateLanguageMapper;
    private final CandidateCvService candidateCvService;
    private final LanguageService languageService;
    private final BusinessRuleService businessRuleService;

    @Autowired
    public CandidateLanguageManager(CandidateLanguageDao candidateLanguageDao,
                                    CandidateLanguageMapper candidateLanguageMapper,
                                    CandidateCvService candidateCvService,
                                    LanguageService languageService,
                                    BusinessRuleService businessRuleService) {
        this.candidateLanguageDao = candidateLanguageDao;
        this.candidateLanguageMapper = candidateLanguageMapper;
        this.candidateCvService = candidateCvService;
        this.languageService = languageService;
        this.businessRuleService = businessRuleService;
    }

    @Override
    public ResponseEntity<DataResult<CandidateLanguageGetDto>> add(CandidateLanguagePostDto candidateLanguagePostDto) {

        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfBooleanValueTrue(
                        candidateCvService.existsById(candidateLanguagePostDto.getCandidateCVId()),
                        "No cv found with given id"
                ),
                businessRuleService.checkIfBooleanValueTrue(
                        languageService.existsById(candidateLanguagePostDto.getLanguageId()),
                        "No language found with given id"
                ),
                businessRuleService.checkIfBooleanValueTrue(
                        !existsByCandidateCVIdAndLanguageId(
                                candidateLanguagePostDto.getCandidateCVId(),
                                candidateLanguagePostDto.getLanguageId()),
                        "This language already exists in your cv."
                )
        ));

        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        } else {
            CandidateLanguage candidateLanguage = candidateLanguageMapper.map(candidateLanguagePostDto);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    candidateLanguageMapper.map(candidateLanguageDao.save(candidateLanguage)))
            );
        }
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateLanguageGetDto>>> getAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(
                candidateLanguageMapper.map(candidateLanguageDao.findAll()))
        );
    }

    @Override
    public boolean existsByCandidateCVIdAndLanguageId(Long cvId, Short languageId) {
        return candidateLanguageDao.existsByCandidateCVIdAndLanguageId(cvId, languageId);
    }
}
