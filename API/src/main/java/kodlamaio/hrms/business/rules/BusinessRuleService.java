package kodlamaio.hrms.business.rules;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.model.concretes.EmployerVerify;
import kodlamaio.hrms.model.concretes.SystemPersonnel;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public interface BusinessRuleService {
    DataResult<Object> checkIfPageNoAndPageSizeValid(int pageNo, int pageSize);
    Result checkIfPasswordsMatch(String password, String passwordCheck);
    Result checkIfEmailContainsWebSiteDomain(String email, String website);
    Result checkIfEmployerVerifyExists(EmployerVerify employerVerify);
    Result checkIfSystemPersonnelExists(SystemPersonnel systemPersonnel);
    DataResult<Object> checkIfIdValid(Long id, String property);
    DataResult<Object> checkIfSalariesValid(int maxSalary, int minSalary);
    DataResult<Object> checkIfOpenPositionValid(int id);
    DataResult<Object> checkIfExpiryDayValid(int id);
    Result checkIfUuidValid(UUID uuid, String person);
    Result checkIfEmailExists(String email);
    Result checkIfUserInformationCorrect(String nationalIdentity, String name, String surname, int year);
    Result checkIfTrimmedStringEmptyOrNull(String str);
    Result checkIfBooleanValueTrue(boolean value, String message);
    Result checkIfFileIsAnImage(String fileName);
    Result checkDates(Date startDate, Date endDate);
    DataResult<Date> checkIfEndDateNull(String endDateString) throws ParseException;
}
