package NationalCountries.exceptions;

import lombok.Getter;

@Getter
public class TokenNotFoundException extends RuntimeException{
    private final int statusCode = 404;

    public TokenNotFoundException(String message) {
        super(message);
    }
}
