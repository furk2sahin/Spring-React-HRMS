package kodlamaio.hrms.core.adapter.abstracts;

public interface UserCheckService {
    boolean validate(String nationalIdentity,
                     String name,
                     String surname,
                     Integer birthYear);
}
