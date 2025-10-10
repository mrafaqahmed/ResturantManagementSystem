package com.restaurant.models;

// Admin class extends User - inheritance
public class Admin extends User {
    
    public Admin(String username, String password) {
        super(username, password, "ADMIN");
    }
    
    // Implementing abstract method (polymorphism)
    @Override
    public void displayDashboard() {
        System.out.println("\n========== ADMIN DASHBOARD ==========");
        System.out.println("Welcome, Admin " + username + "!");
        System.out.println("You have full access to manage the restaurant.");
        System.out.println("====================================\n");
    }
}
