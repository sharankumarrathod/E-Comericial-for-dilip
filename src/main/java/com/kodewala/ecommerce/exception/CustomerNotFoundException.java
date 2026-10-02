package com.kodewala.ecommerce.exception;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(int customerId) {
        super("Customer with ID " + customerId + " does not exist.");
    }
}
