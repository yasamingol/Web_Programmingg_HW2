package NationalCountries.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class LoginException extends RuntimeException {
    private final ResponseEntity<Object> responseEntity;

    public LoginException(String message) {
        super(message);
        this.responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
        this.responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

}
