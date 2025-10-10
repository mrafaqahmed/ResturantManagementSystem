package com.restaurant.exceptions;

// Custom exception for payment failures
public class PaymentFailedException extends Exception {
    
    public PaymentFailedException(String message) {
        super(message);
    }
}
