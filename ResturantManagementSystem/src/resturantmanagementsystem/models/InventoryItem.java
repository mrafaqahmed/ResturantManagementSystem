package com.restaurant.models;

// InventoryItem class for managing stock
public class InventoryItem {
    private String itemName;
    private int quantity;
    private int minQuantity;  // Threshold for low stock alert
    
    public InventoryItem(String itemName, int quantity, int minQuantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }
    
    // Reduce stock after order
    public void reduceStock(int amount) {
        this.quantity -= amount;
    }
    
    // Add stock
    public void addStock(int amount) {
        this.quantity += amount;
    }
    
    // Check if stock is low
    public boolean isLowStock() {
        return quantity <= minQuantity;
    }
    
    // Getters and Setters
    public String getItemName() {
        return itemName;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getMinQuantity() {
        return minQuantity;
    }
    
    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }
    
    public void displayItem() {
        System.out.println(itemName + " - Qty: " + quantity + 
                         (isLowStock() ? " [LOW STOCK!]" : ""));
    }
}
