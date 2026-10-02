package com.kodewala.ecommerce.exception;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }

    public InvalidQuantityException(int quantity) {
        super("Invalid quantity: " + quantity + ". Quantity must be greater than 0.");
    }
}
