package irctc_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalException
{

    //👉 This will Handel Validation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validations(MethodArgumentNotValidException e)
    {
        Map<String,String> response = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(
                error -> {
                        response.put(error.getField() , error.getDefaultMessage());
                }
        );

        return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
    }



    //👉 This will Handel Duplicate Element In DB exception
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> Duplicate_Entries_in_DB(SQLIntegrityConstraintViolationException e)
    {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementFound(NoSuchElementException e)
    {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


}
