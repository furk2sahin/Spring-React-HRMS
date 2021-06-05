package kodlamaio.hrms.business.concretes.cv;

import kodlamaio.hrms.business.abstracts.cv.CandidateCvService;
import kodlamaio.hrms.business.abstracts.cv.CandidateTechnologyService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.resultchecker.ResultChecker;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.mapper.CandidateTechnologyMapper;
import kodlamaio.hrms.model.concretes.cv.CandidateTechnology;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateTechnologyGetDto;
import kodlamaio.hrms.model.dtos.concretes.cv.CandidateTechnologyPostDto;
import kodlamaio.hrms.repositories.cv.CandidateTechnologyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CandidateTechnologyManager implements CandidateTechnologyService {

    private final CandidateTechnologyDao candidateTechnologyDao;
    private final CandidateTechnologyMapper candidateTechnologyMapper;
    private final CandidateCvService candidateCvService;
    private final BusinessRuleService businessRuleService;

    @Autowired
    public CandidateTechnologyManager(CandidateTechnologyDao candidateTechnologyDao,
                                      CandidateTechnologyMapper candidateTechnologyMapper,
                                      CandidateCvService candidateCvService,
                                      BusinessRuleService businessRuleService) {
        this.candidateTechnologyDao = candidateTechnologyDao;
        this.candidateTechnologyMapper = candidateTechnologyMapper;
        this.candidateCvService = candidateCvService;
        this.businessRuleService = businessRuleService;
    }

    @Override
    public ResponseEntity<DataResult<CandidateTechnologyGetDto>> add(CandidateTechnologyPostDto candidateTechnologyPostDto) {
        Result result = ResultChecker.check(Arrays.asList(
                businessRuleService.checkIfBooleanValueTrue(
                        candidateCvService.existsById(candidateTechnologyPostDto.getCandidateCVId()),
                        "No cv found by given id."
                ),
                businessRuleService.checkIfBooleanValueTrue(
                        !existsByCandidateCVIdAndTechnologyName(
                                candidateTechnologyPostDto.getCandidateCVId(),
                                candidateTechnologyPostDto.getTechnologyName()
                        ),
                        "This technology already exists in your cv."
                )
        ));

        if(result.isSuccess()){
            CandidateTechnology candidateTechnology = candidateTechnologyMapper.map(candidateTechnologyPostDto);
            return ResponseEntity.ok(new SuccessDataResult<>(
                    candidateTechnologyMapper.map(candidateTechnologyDao.save(candidateTechnology)))
            );
        } else {
            return ResponseEntity.badRequest().body(new ErrorDataResult<>(result.getMessage()));
        }
    }

    @Override
    public ResponseEntity<DataResult<List<CandidateTechnologyGetDto>>> getAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(
                candidateTechnologyMapper.map(candidateTechnologyDao.findAll()))
        );
    }

    @Override
    public boolean existsByCandidateCVIdAndTechnologyName(Long id, String name) {
        return candidateTechnologyDao.existsByCandidateCVIdAndTechnologyName(id, name);
    }
}
