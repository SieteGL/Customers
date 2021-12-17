package siete.springframework.customersapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionController {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<Object> exception(CustomerNotFoundException exception){
        return new ResponseEntity<>("*** Customer Not Found ***", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = CustomerIdExistException.class)
    public ResponseEntity<Object> exception(CustomerIdExistException exception){
        return new ResponseEntity<>("*** Customer Id Already exist ***", HttpStatus.NOT_FOUND);
    }
}
