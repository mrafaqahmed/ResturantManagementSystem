package com.restaurant.models;

// Dessert class extends MenuItem
public class Dessert extends MenuItem {
    private String flavor;
    
    public Dessert(int id, String name, double price, String flavor) {
        super(id, name, price, "Dessert");
        this.flavor = flavor;
    }
    
    @Override
    public String getDescription() {
        return "Sweet " + flavor + " flavored dessert";
    }
    
    public String getFlavor() {
        return flavor;
    }
}
