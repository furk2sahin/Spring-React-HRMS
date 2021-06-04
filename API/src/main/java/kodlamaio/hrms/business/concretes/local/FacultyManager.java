package kodlamaio.hrms.business.concretes.local;

import kodlamaio.hrms.business.abstracts.local.FacultyService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.local.Faculty;
import kodlamaio.hrms.repositories.local.FacultyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyManager implements FacultyService {

    private FacultyDao facultyDao;

    @Autowired
    public FacultyManager(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    @Override
    public ResponseEntity<DataResult<List<Faculty>>> findAll() {
        return ResponseEntity.ok(new SuccessDataResult<>(facultyDao.findAll(), "Data listed successfully"));
    }

    @Override
    public ResponseEntity<DataResult<Faculty>> findById(Integer id) {
        Faculty faculty = facultyDao.findById(id).orElse(null);
        if(faculty == null){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("No faculty found with given id"));
        }
        return ResponseEntity.ok(new SuccessDataResult<>(faculty, "Faculty found."));
    }

    @Override
    public ResponseEntity<DataResult<List<Faculty>>> findByUniversityId(Integer id) {
        List<Faculty> faculties = facultyDao.findAllByUniversityId(id);
        if(faculties.isEmpty()){
            return ResponseEntity.ok(new ErrorDataResult<>(
                    faculties,
                    "No data was found."
            ));
        }
        return ResponseEntity.ok(new SuccessDataResult<>(
                faculties,
                "Data listed successfully."
        ));
    }
}
