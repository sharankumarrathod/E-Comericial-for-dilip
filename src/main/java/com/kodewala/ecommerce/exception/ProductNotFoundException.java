package com.kodewala.ecommerce.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(int productId) {
        super("Product with ID " + productId + " does not exist.");
    }
}
