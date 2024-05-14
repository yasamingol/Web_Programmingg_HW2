package NationalCountries.exceptions;

import lombok.Getter;

@Getter
public class CountryNotFoundException extends RuntimeException {
    private final int statusCode;

    public CountryNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
