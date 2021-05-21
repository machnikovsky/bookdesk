package pl.machnikovsky.bookdesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BookDeskExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> bookNotFoundExceptionHandler(BookNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(
                e.getMessage(),
                status,
                status.value()
        );
        return new ResponseEntity<>(exceptionResponseEntity, status);
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> requestLimitExceededException(UserNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity(
                e.getMessage(),
                status,
                status.value()
        );
        return new ResponseEntity<>(exceptionResponseEntity, status);
    }

}
