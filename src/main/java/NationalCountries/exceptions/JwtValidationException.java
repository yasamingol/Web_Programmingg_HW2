package NationalCountries.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JwtValidationException extends RuntimeException {
    private final HttpStatus statusCode;

    public JwtValidationException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
