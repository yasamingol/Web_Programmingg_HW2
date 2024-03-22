package edu.webclass.restapi.Product.Management.System.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private final int statusCode;

    public ProductNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
