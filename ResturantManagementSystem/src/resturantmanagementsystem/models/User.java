package com.restaurant.models;

// Abstract User class demonstrating abstraction
public abstract class User {
    protected String username;  // protected modifier - accessible to subclasses
    protected String password;
    protected String role;
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    // Getters
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract void displayDashboard();
}
