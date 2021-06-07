package kodlamaio.hrms.business.rules;

import com.google.common.base.Strings;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapter.abstracts.UserCheckService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.EmployerVerify;
import kodlamaio.hrms.model.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class BusinessRuleManager implements BusinessRuleService{

    private final UserService userService;
    private final UserCheckService userCheckService;

    @Autowired
    public BusinessRuleManager(UserService userService, UserCheckService userCheckService) {
        this.userService = userService;
        this.userCheckService = userCheckService;
    }

    @Override
    public DataResult<Object> checkIfPageNoAndPageSizeValid(int pageNo, int pageSize){
        if(pageSize < 1){
            return new ErrorDataResult<>("Page size is not valid.");
        } else if(pageNo < 1){
            return new ErrorDataResult<>("Page number is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    @Override
    public Result checkIfPasswordsMatch(String password, String passwordCheck){
        if(!password.equals(passwordCheck)) {
            return new ErrorResult(
                    "Passwords did not match."
            );
        } else {
            return new SuccessResult();
        }
    }

    @Override
    public  Result checkIfEmailContainsWebSiteDomain(String email, String website){
        if(!email.split("@")[1].contains(website)){
            return new ErrorResult(
                    "This email does not contain web site's domain."
            );
        } else {
            return new SuccessResult();
        }
    }

    @Override
    public Result checkIfEmployerVerifyExists(EmployerVerify employerVerify){
        if(employerVerify == null){
            return new ErrorDataResult<>("This employer uuid does not match with any user.");
        } else {
            return new SuccessResult();
        }
    }

    @Override
    public Result checkIfSystemPersonnelExists(SystemPersonnel systemPersonnel){
        if(systemPersonnel == null) {
            return new ErrorDataResult<>("This system personnel uuid does not match with any user.");
        } else {
            return new SuccessResult();
        }
    }

    @Override
    public DataResult<Object> checkIfIdValid(Long id, String property){
        if(id == null)
            return new ErrorDataResult<>(property + " Id is not valid.");
        else if(id < 1)
            return new ErrorDataResult<>(property + " Id is not valid.");
        else
            return new SuccessDataResult<>();

    }

    @Override
    public DataResult<Object> checkIfSalariesValid(int maxSalary, int minSalary){
        if(maxSalary < 1){
            return new ErrorDataResult<>("max salary is not valid.");
        } else if(minSalary < 1){
            return new ErrorDataResult<>("min salary is not valid.");
        } else if(maxSalary < minSalary){
            return new ErrorDataResult<>("min salary cannot be greater than max salary.");
        } else{
            return new SuccessDataResult<>();
        }
    }

    @Override
    public DataResult<Object> checkIfOpenPositionValid(int id){
        if(id < 1){
            return new ErrorDataResult<>("Open position number is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    @Override
    public DataResult<Object> checkIfExpiryDayValid(int id){
        if(id < 1){
            return new ErrorDataResult<>("Expiry Day is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    @Override
    public Result checkIfUuidValid(UUID uuid, String person){
        String regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(regexp);
        if(!pattern.matcher(uuid.toString()).matches()){
            return new ErrorDataResult(person + " UUID not valid.");
        } else {
            return new SuccessResult();
        }
    }

    @Override
    public Result checkIfEmailExists(String email){
        if(userService.existsByEmail(email)){
            return new ErrorResult(
                    "This email already taken."
            );
        } else {
            return new SuccessResult();
        }
    }

    @Override
    public Result checkIfUserInformationCorrect(String nationalIdentity, String name, String surname, int year){
        if(!userCheckService.validate(nationalIdentity, name, surname, year)) {
            return new ErrorResult(
                    "User information was incorrect."
            );
        } else {
            return new SuccessResult();
        }
    }

    @Override
    public Result checkIfTrimmedStringEmptyOrNull(String str){
        if(Strings.isNullOrEmpty(str)){
            return new ErrorDataResult<>("Parameter cannot be empty.");
        } else {
            if(str.trim().length() == 0){
                return new ErrorDataResult<>("Parameter cannot be empty.");
            }
            return new SuccessResult();
        }

    }

    @Override
    public Result checkIfBooleanValueTrue(boolean value, String message) {
        if(value){
            return new SuccessResult();
        } else {
            return new ErrorDataResult<>(message);
        }
    }

    @Override
    public Result checkIfFileIsAnImage(String fileName) {
        if(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg")){
            return new SuccessResult();
        } else {
            return new ErrorDataResult<>("Accepted image formats: .png, .jpg, .jpeg");
        }
    }

    @Override
    public Result checkDates(Date startDate, Date endDate){
        if(startDate.after(new Date())){
            return new ErrorResult("Start date should be before than now");
        } else if(endDate != null ){
            if(startDate.after(endDate))
                return new ErrorResult("Start date cannot be greater than end date");
            else return new SuccessResult();
        } else return new SuccessResult();
    }

    @Override
    public DataResult<Date> checkIfEndDateNull(String endDateString) throws ParseException {
        if(endDateString != null){
            if(Pattern.compile("^(19|20)\\d\\d(-)(0[1-9]|1[012])(-)(0[1-9]|[12][0-9]|3[01])$")
                    .matcher(endDateString).matches()){
                return new SuccessDataResult<>(new SimpleDateFormat("yyyy-MM-dd").parse(endDateString));
            }
        }
        return new ErrorDataResult<>("Wrong end date format");
    }
}
