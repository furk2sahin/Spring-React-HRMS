package kodlamaio.hrms.core.utilities.resultchecker;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

import java.util.List;

public class ResultChecker {
    public static Result check(List<Result> results){
        for (Result result : results){
            if(!result.isSuccess()){
                return result;
            }
        }
        return new SuccessResult();
    }
}
