package kodlamaio.hrms.business.concretes.local;

import kodlamaio.hrms.business.abstracts.local.UniversityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.local.University;
import kodlamaio.hrms.repositories.local.UniversityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityManager implements UniversityService {

    private UniversityDao universityDao;

    @Autowired
    public UniversityManager(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    @Override
    public ResponseEntity<DataResult<List<University>>> findAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(universityDao.findAll(), "Data listed successfully"));
    }

    @Override
    public ResponseEntity<DataResult<University>> findById(Integer id) {
        University university = universityDao.findById(id).orElse(null);
        if(university == null){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("No university found with given id"));
        }
        return ResponseEntity.ok(new SuccessDataResult<>(university, "University found."));
    }

    @Override
    public ResponseEntity<DataResult<List<University>>> findByCityId(Short id) {
        List<University> universities = universityDao.findAllByCityId(id);
        if(universities.isEmpty()){
            return ResponseEntity.ok(new ErrorDataResult<>(
                    universities,
                    "No data was found."
            ));
        }
        return ResponseEntity.ok(new SuccessDataResult<>(
                universities,
                "Data listed successfully."
        ));
    }
}
