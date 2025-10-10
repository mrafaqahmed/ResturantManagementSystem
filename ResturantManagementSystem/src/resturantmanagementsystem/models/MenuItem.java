package com.restaurant.models;

// Abstract class demonstrating abstraction and inheritance
public abstract class MenuItem {
    // Private fields demonstrating encapsulation
    private int id;
    private String name;
    private double price;
    private String category;
    private boolean available;
    
    // Constructor to initialize menu item
    public MenuItem(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = true;
    }
    
    // Getter methods (Encapsulation - providing controlled access)
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getCategory() {
        return category;
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    // Setter methods
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    // Abstract method - must be implemented by subclasses (polymorphism)
    public abstract String getDescription();
    
    // Method to display item details
    public void displayItem() {
        System.out.println("ID: " + id + " | " + name + " | Rs." + price + " | " + category);
        System.out.println("   Description: " + getDescription());
        System.out.println("   Status: " + (available ? "Available" : "Out of Stock"));
    }
}
