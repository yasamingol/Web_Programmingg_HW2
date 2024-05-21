package NationalCountries.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class RegistrationException extends RuntimeException {
    private final ResponseEntity<Object> responseEntity;

    public RegistrationException(String message) {
        super(message);
        this.responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
        this.responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
