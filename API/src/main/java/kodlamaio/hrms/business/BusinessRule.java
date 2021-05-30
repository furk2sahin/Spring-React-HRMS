package kodlamaio.hrms.business;

import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.model.concretes.EmployerVerify;
import kodlamaio.hrms.model.concretes.SystemPersonnel;

import java.util.UUID;
import java.util.regex.Pattern;

public class BusinessRule {

    public static DataResult<Object> checkIfPageNoAndPageSizeValid(int pageNo, int pageSize){
        if(pageSize < 1){
            return new ErrorDataResult<>("Page size is not valid.");
        } else if(pageNo < 1){
            return new ErrorDataResult<>("Page number is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    public static Result checkIfPasswordsMatch(String password, String passwordCheck){
        if(!password.equals(passwordCheck)) {
            return new ErrorResult(
                    "Passwords did not match."
            );
        } else {
            return new SuccessResult();
        }
    }

    public static Result checkIfEmailContainsWebSiteDomain(String email, String website){
        if(!email.split("@")[1].contains(website)){
            return new ErrorResult(
                    "This email does not contain web site's domain."
            );
        } else {
            return new SuccessResult();
        }
    }

    public static Result checkIfEmployerVerifyExists(EmployerVerify employerVerify){
        if(employerVerify == null){
            return new ErrorDataResult<>("This employer uuid does not match with any user.");
        } else {
            return new SuccessResult();
        }
    }

    public static Result checkIfSystemPersonnelExists(SystemPersonnel systemPersonnel){
        if(systemPersonnel == null) {
            return new ErrorDataResult<>("This system personnel uuid does not match with any user.");
        } else {
            return new SuccessResult();
        }
    }

    public static DataResult<Object> checkIfIdValid(Long id){
        if(id < 1){
            return new ErrorDataResult<>("Id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    public static DataResult<Object> checkIfCityIdValid(Long id){
        if(id < 1 || id > 81){
            return new ErrorDataResult<>("City id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    public static DataResult<Object> checkIfJobIdValid(Long id){
        if(id < 1){
            return new ErrorDataResult<>("Job id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    public static DataResult<Object> checkIfEmployerIdValid(Long id){
        if(id < 1){
            return new ErrorDataResult<>("Employer id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    public static DataResult<Object> checkIfSalariesValid(int maxSalary, int minSalary){
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

    public static DataResult<Object> checkIfOpenPositionValid(int id){
        if(id < 1){
            return new ErrorDataResult<>("Job id is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    public static DataResult<Object> checkIfExpiryDayValid(int id){
        if(id < 1){
            return new ErrorDataResult<>("Expiry Day is not valid.");
        } else {
            return new SuccessDataResult<>();
        }
    }

    public static Result checkIfUuidValid(UUID uuid, String person){
        String regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(regexp);
        if(!pattern.matcher(uuid.toString()).matches()){
            return new ErrorDataResult(person + " UUID not valid.");
        } else {
            return new SuccessResult();
        }
    }
}
