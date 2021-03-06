package kodlamaio.hrms.core.exceptionHandler;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDataResult<Object>> handleValidationExceptions(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<>();

        exceptions.getBindingResult().getFieldErrors()
                .forEach(fieldError -> validationErrors
                        .put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.badRequest().body(new ErrorDataResult<>(validationErrors, "Validation Errors"));
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<Object> handleNullProperties(PropertyValueException ex) {
        String[] message = ex.getMessage().split("\\.");
        return new ResponseEntity<>(new ErrorResult(message[message.length-1] + " was empty"),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Result> handleMediaType(HttpMediaTypeNotSupportedException ex) {
        return new ResponseEntity<>(new ErrorResult("Unsupported Media Type"),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<Result> handleSizeLimit(SizeLimitExceededException ex) {
        return new ResponseEntity<>(new ErrorResult("Max File Size: 5 MB"),
                HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Result> handleNumberFormat(NumberFormatException ex) {
        return new ResponseEntity<>(new ErrorResult(ex.getMessage()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<Result> handleAmazonS3(AmazonS3Exception ex) {
        return new ResponseEntity<>(new ErrorResult("Amazon S3 Connection Error"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleJsonExceptions(HttpMessageNotReadableException ex) {
            return new ResponseEntity<>(
                    new ErrorResult(ex.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE
            );
    }
}