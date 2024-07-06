package user.controllers;

import user.exceptions.JwtValidationException;
import user.exceptions.LoginException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(JwtValidationException.class)
    public ResponseEntity<String> handleCountryNotFoundException(JwtValidationException ex) {
        String errorMessage = "Error Code: " + ex.getStatusCode() + ", Message: " + ex.getMessage();
        return ResponseEntity.status(ex.getStatusCode()).body(errorMessage);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleLoginException(LoginException ex) {
        return ex.getResponseEntity();
    }
}