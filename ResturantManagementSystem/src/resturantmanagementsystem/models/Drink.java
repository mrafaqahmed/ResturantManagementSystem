package com.restaurant.models;

// Drink class extends MenuItem
public class Drink extends MenuItem {
    private String size;
    private boolean cold;
    
    public Drink(int id, String name, double price, String size, boolean cold) {
        super(id, name, price, "Drink");
        this.size = size;
        this.cold = cold;
    }
    
    @Override
    public String getDescription() {
        return size + " " + (cold ? "cold" : "hot") + " drink";
    }
    
    public String getSize() {
        return size;
    }
    
    public boolean isCold() {
        return cold;
    }
}
