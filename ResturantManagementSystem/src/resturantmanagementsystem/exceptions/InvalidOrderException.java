package com.restaurant.exceptions;

// Custom exception for invalid orders
public class InvalidOrderException extends Exception {
    
    public InvalidOrderException(String message) {
        super(message);
    }
}
