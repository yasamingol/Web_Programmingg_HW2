package edu.webclass.restapi.Product.Management.System.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    private int statusCode;

    public ProductNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}