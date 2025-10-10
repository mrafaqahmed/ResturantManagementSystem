package com.restaurant.models;

// CashPayment extends Payment - demonstrating inheritance and polymorphism
public class CashPayment extends Payment {
    private double amountGiven;
    private double change;
    
    public CashPayment(double amount, double amountGiven) {
        super(amount);
        this.amountGiven = amountGiven;
        this.change = 0;
    }
    
    @Override
    public boolean processPayment(double amount) {
        if (amountGiven >= amount) {
            this.change = amountGiven - amount;
            this.paymentSuccess = true;
            System.out.println("Cash payment successful!");
            if (change > 0) {
                System.out.println("Your change: Rs." + change);
            }
            return true;
        } else {
            System.out.println("Insufficient cash! Need Rs." + (amount - amountGiven) + " more.");
            return false;
        }
    }
    
    @Override
    public String getPaymentMethod() {
        return "Cash";
    }
    
    public double getChange() {
        return change;
    }
}
