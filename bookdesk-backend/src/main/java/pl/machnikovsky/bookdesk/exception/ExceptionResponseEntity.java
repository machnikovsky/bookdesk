package pl.machnikovsky.bookdesk.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponseEntity {

    private final String message;
    private final HttpStatus httpStatus;
    private final int statusCode;

    public ExceptionResponseEntity(String message, HttpStatus httpStatus, int statusCode) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
