package com.restaurant.models;

// Burger class extends MenuItem - demonstrating inheritance
public class Burger extends MenuItem {
    private String type;
    private boolean cheese;
    
    // Constructor with super keyword
    public Burger(int id, String name, double price, String type, boolean cheese) {
        super(id, name, price, "Burger");
        this.type = type;
        this.cheese = cheese;
    }
    
    // Overriding abstract method (polymorphism)
    @Override
    public String getDescription() {
        return type + " burger " + (cheese ? "with extra cheese" : "regular");
    }
    
    public String getType() {
        return type;
    }
    
    public boolean hasCheese() {
        return cheese;
    }
}
