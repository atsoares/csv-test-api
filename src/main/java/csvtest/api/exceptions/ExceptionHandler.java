package csvtest.api.exceptions;

import csvtest.api.tos.response.ErrorResponseTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CSVInvalidException.class)
    public ResponseEntity<ErrorResponseTO> handleCsvInvalidException(CSVInvalidException ex) {
        return new ResponseEntity<>(new ErrorResponseTO("Primary key is mandatory"), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ObjectDoesNotExistException.class)
    public ResponseEntity<ErrorResponseTO> handleObjectDoesNotExistException(ObjectDoesNotExistException ex) {
        return new ResponseEntity<>(new ErrorResponseTO("Object does not exist"), HttpStatus.NOT_FOUND);
    }
}