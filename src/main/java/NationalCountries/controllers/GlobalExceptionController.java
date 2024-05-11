package NationalCountries.controllers;

import NationalCountries.exceptions.CountryNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(CountryNotFoundException ex) {
        String errorMessage = "Error Code: " + ex.getStatusCode() + ", Message: " + ex.getMessage();
        return ResponseEntity.status(ex.getStatusCode()).body(errorMessage);
    }
}