package com.restaurant.models;

// CardPayment extends Payment - demonstrating polymorphism
public class CardPayment extends Payment {
    private String cardNumber;
    private String cardHolderName;
    
    public CardPayment(double amount, String cardNumber, String cardHolderName) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }
    
    @Override
    public boolean processPayment(double amount) {
        // Simulate card payment processing
        if (cardNumber.length() >= 12) {
            this.paymentSuccess = true;
            System.out.println("Card payment successful!");
            System.out.println("Card ending in: " + cardNumber.substring(cardNumber.length() - 4));
            return true;
        } else {
            System.out.println("Invalid card number!");
            return false;
        }
    }
    
    @Override
    public String getPaymentMethod() {
        return "Card (" + cardHolderName + ")";
    }
}
