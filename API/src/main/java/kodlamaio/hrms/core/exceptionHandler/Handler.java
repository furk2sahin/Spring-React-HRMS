package kodlamaio.hrms.core.exceptionHandler;

import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<>();

        exceptions.getBindingResult().getFieldErrors()
                .forEach(fieldError -> validationErrors
                        .put(fieldError.getField(), fieldError.getDefaultMessage()));

        return new ErrorDataResult<>(validationErrors, "Validation Errors");
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<Object> handleNullProperties(PropertyValueException ex) {
        String[] message = ex.getMessage().split("\\.");
        return new ResponseEntity<>(new ErrorResult(message[message.length-1] + " was empty"),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleJsonExceptions(HttpMessageNotReadableException ex) {
        if(ex.getMessage().contains("birthDate")){
            return new ResponseEntity<>(
                    new ErrorResult("Wrong birth date format. Pattern should be like 2015-05-20 (YYYY-mm-dd)"),
                    HttpStatus.NOT_ACCEPTABLE
            );
        }
        return new ResponseEntity<>(new ErrorResult(ex.getMessage()),
                HttpStatus.NOT_ACCEPTABLE);
    }
}