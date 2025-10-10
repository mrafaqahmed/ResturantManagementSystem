package com.restaurant.models;

import com.restaurant.interfaces.Payable;

// Abstract Payment class implementing Payable interface
public abstract class Payment implements Payable {
    protected double amount;
    protected boolean paymentSuccess;
    
    public Payment(double amount) {
        this.amount = amount;
        this.paymentSuccess = false;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public boolean isPaymentSuccess() {
        return paymentSuccess;
    }
    
    // Common receipt generation method
    @Override
    public void generateReceipt() {
        if (paymentSuccess) {
            System.out.println("\n========== PAYMENT RECEIPT ==========");
            System.out.println("Payment Method: " + getPaymentMethod());
            System.out.println("Amount Paid: Rs." + amount);
            System.out.println("Status: SUCCESS");
            System.out.println("Thank you for your payment!");
            System.out.println("====================================\n");
        } else {
            System.out.println("Payment failed. No receipt generated.");
        }
    }
}
