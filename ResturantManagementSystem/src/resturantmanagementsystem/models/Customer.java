package com.restaurant.models;

// Customer class extends User
public class Customer extends User {
    
    public Customer(String username, String password) {
        super(username, password, "CUSTOMER");
    }
    
    @Override
    public void displayDashboard() {
        System.out.println("\n========== CUSTOMER DASHBOARD ==========");
        System.out.println("Welcome, " + username + "!");
        System.out.println("Browse our menu and place your order.");
        System.out.println("=======================================\n");
    }
}
