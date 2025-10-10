package com.restaurant.services;

import com.restaurant.models.InventoryItem;
import com.restaurant.exceptions.OutOfStockException;
import java.util.ArrayList;

// InventoryManager to manage stock using ArrayList
public class InventoryManager {
    private static InventoryManager instance;
    private ArrayList<InventoryItem> inventory;
    
    private InventoryManager() {
        inventory = new ArrayList<InventoryItem>();
        initializeInventory();
    }
    
    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }
    
    // Initialize sample inventory
    private void initializeInventory() {
        inventory.add(new InventoryItem("Cheese", 50, 10));
        inventory.add(new InventoryItem("Tomato", 40, 8));
        inventory.add(new InventoryItem("Chicken", 30, 5));
        inventory.add(new InventoryItem("Beef", 25, 5));
        inventory.add(new InventoryItem("Buns", 100, 20));
        inventory.add(new InventoryItem("Potatoes", 60, 15));
        inventory.add(new InventoryItem("Drinks", 80, 20));
        inventory.add(new InventoryItem("Ice Cream", 40, 10));
    }
    
    // Add inventory item
    public void addInventoryItem(InventoryItem item) {
        inventory.add(item);
        System.out.println("Inventory item added!");
    }
    
    // Update stock quantity
    public void updateStock(String itemName, int quantity) throws OutOfStockException {
        for (InventoryItem item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                if (quantity < 0 && Math.abs(quantity) > item.getQuantity()) {
                    throw new OutOfStockException(itemName, item.getQuantity());
                }
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Stock updated: " + itemName + " -> " + item.getQuantity());
                return;
            }
        }
        System.out.println("Item not found in inventory!");
    }
    
    // Check stock availability
    public boolean checkStock(String itemName, int required) {
        for (InventoryItem item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item.getQuantity() >= required;
            }
        }
        return false;
    }
    
    // Display all inventory
    public void displayInventory() {
        System.out.println("\n========== INVENTORY STATUS ==========");
        for (InventoryItem item : inventory) {
            item.displayItem();
        }
        System.out.println("=====================================\n");
    }
    
    // Display low stock items
    public void displayLowStockItems() {
        System.out.println("\n========== LOW STOCK ALERT ==========");
        boolean found = false;
        for (InventoryItem item : inventory) {
            if (item.isLowStock()) {
                System.out.println("⚠️  " + item.getItemName() + " - Only " + 
                                 item.getQuantity() + " left!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("All items are well stocked.");
        }
        System.out.println("====================================\n");
    }
    
    // Get inventory item
    public InventoryItem getInventoryItem(String itemName) {
        for (InventoryItem item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    public ArrayList<InventoryItem> getAllItems() {
        return inventory;
    }
}
