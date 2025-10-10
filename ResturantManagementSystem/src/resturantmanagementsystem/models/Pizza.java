package com.restaurant.models;

// Pizza class extends MenuItem - demonstrating inheritance
public class Pizza extends MenuItem {
    private String size;
    private String crust;
    
    // Constructor using super keyword to call parent constructor
    public Pizza(int id, String name, double price, String size, String crust) {
        super(id, name, price, "Pizza");
        this.size = size;
        this.crust = crust;
    }
    
    // Implementing abstract method from parent class (polymorphism)
    @Override
    public String getDescription() {
        return "Delicious " + size + " pizza with " + crust + " crust";
    }
    
    public String getSize() {
        return size;
    }
    
    public String getCrust() {
        return crust;
    }
}
