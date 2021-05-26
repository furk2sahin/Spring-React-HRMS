package kodlamaio.hrms.business.abstracts;


import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.model.concretes.Candidate;

import java.util.List;

public interface CandidateService {
    DataResult<Candidate> add(Candidate candidate);
    DataResult<List<Candidate>> getAll();
}
