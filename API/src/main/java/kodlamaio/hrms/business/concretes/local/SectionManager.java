package kodlamaio.hrms.business.concretes.local;

import kodlamaio.hrms.business.abstracts.local.SectionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.model.concretes.local.Section;
import kodlamaio.hrms.model.concretes.local.University;
import kodlamaio.hrms.repositories.local.SectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionManager implements SectionService {

    private SectionDao sectionDao;

    @Autowired
    public SectionManager(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Override
    public ResponseEntity<DataResult<Section>> findById(Integer id) {
        Section section = sectionDao.findById(id).orElse(null);
        if(section == null){
            return ResponseEntity.badRequest().body(new ErrorDataResult<>("No section found with given id"));
        }
        return ResponseEntity.ok(new SuccessDataResult<>(section, "Section found."));
    }

    @Override
    public ResponseEntity<DataResult<List<Section>>> findByFacultyId(Integer id) {
        List<Section> sections = sectionDao.findAllByFacultyId(id);
        if(sections.isEmpty()){
            return ResponseEntity.ok(new ErrorDataResult<>(
                    sections,
                    "No data was found."
            ));
        }
        return ResponseEntity.ok(new SuccessDataResult<>(
                sections,
                "Data listed successfully."
        ));
    }

    @Override
    public boolean existsById(Integer id) {
        return sectionDao.existsById(id);
    }
}
