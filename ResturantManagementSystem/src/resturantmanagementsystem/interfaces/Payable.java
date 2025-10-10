package com.restaurant.interfaces;

// Interface for payment operations
public interface Payable {
    boolean processPayment(double amount);
    void generateReceipt();
    String getPaymentMethod();
}
