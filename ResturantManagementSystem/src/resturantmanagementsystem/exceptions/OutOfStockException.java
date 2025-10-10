package com.restaurant.exceptions;

// Custom exception class - demonstrating exception handling
public class OutOfStockException extends Exception {
    
    public OutOfStockException(String message) {
        super(message);
    }
    
    public OutOfStockException(String itemName, int available) {
        super("Out of Stock: " + itemName + " (Available: " + available + ")");
    }
}
