package com.restaurant.models;

// Fries class extends MenuItem
public class Fries extends MenuItem {
    private String size;
    
    public Fries(int id, String name, double price, String size) {
        super(id, name, price, "Fries");
        this.size = size;
    }
    
    @Override
    public String getDescription() {
        return "Crispy golden " + size + " fries";
    }
    
    public String getSize() {
        return size;
    }
}
