package NationalCountries.exceptions;

public class CountryNotFoundException extends RuntimeException {
    private final int statusCode;

    public CountryNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
