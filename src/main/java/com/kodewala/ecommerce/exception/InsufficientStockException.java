package com.kodewala.ecommerce.exception;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(int productId, int requested, int available) {
        super("Insufficient stock for Product ID " + productId + ". Requested: " + requested + ", Available: " + available);
    }
}
