package com.restaurant.services;

import com.restaurant.models.*;
import java.util.ArrayList;

// MenuManager class to manage all menu items using ArrayList
public class MenuManager {
    // Static keyword - single instance shared across application
    private static MenuManager instance;
    private ArrayList<MenuItem> menuItems;
    
    // Private constructor for singleton pattern
    private MenuManager() {
        menuItems = new ArrayList<MenuItem>();
        initializeSampleMenu();
    }
    
    // Static method to get single instance
    public static MenuManager getInstance() {
        if (instance == null) {
            instance = new MenuManager();
        }
        return instance;
    }
    
    // Initialize sample menu items
    private void initializeSampleMenu() {
        // Pizzas
        menuItems.add(new Pizza(1, "Cheese Pizza", 599.0, "Medium", "Thin"));
        menuItems.add(new Pizza(2, "Pepperoni Pizza", 799.0, "Large", "Thick"));
        menuItems.add(new Pizza(3, "Veggie Pizza", 699.0, "Medium", "Thin"));
        
        // Burgers
        menuItems.add(new Burger(4, "Chicken Burger", 299.0, "Grilled", true));
        menuItems.add(new Burger(5, "Beef Burger", 399.0, "BBQ", true));
        menuItems.add(new Burger(6, "Veggie Burger", 249.0, "Garden", false));
        
        // Fries
        menuItems.add(new Fries(7, "Regular Fries", 149.0, "Medium"));
        menuItems.add(new Fries(8, "Cheese Fries", 199.0, "Large"));
        
        // Drinks
        menuItems.add(new Drink(9, "Coca Cola", 99.0, "Regular", true));
        menuItems.add(new Drink(10, "Orange Juice", 129.0, "Large", true));
        menuItems.add(new Drink(11, "Coffee", 149.0, "Regular", false));
        
        // Desserts
        menuItems.add(new Dessert(12, "Chocolate Cake", 299.0, "Chocolate"));
        menuItems.add(new Dessert(13, "Ice Cream", 199.0, "Vanilla"));
    }
    
    // Add new menu item
    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
        System.out.println("Menu item added successfully!");
    }
    
    // Remove menu item by ID - using index-based loop to avoid ConcurrentModificationException
    public boolean removeMenuItem(int id) {
        for (int i = 0; i < menuItems.size(); i++) {
            if (menuItems.get(i).getId() == id) {
                menuItems.remove(i);
                System.out.println("Menu item removed successfully!");
                return true;
            }
        }
        System.out.println("Item not found!");
        return false;
    }
    
    // Update menu item price
    public boolean updateItemPrice(int id, double newPrice) {
        for (MenuItem item : menuItems) {
            if (item.getId() == id) {
                item.setPrice(newPrice);
                System.out.println("Price updated successfully!");
                return true;
            }
        }
        System.out.println("Item not found!");
        return false;
    }
    
    // Get menu item by ID
    public MenuItem getItemById(int id) {
        for (MenuItem item : menuItems) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    
    // Display all menu items
    public void displayAllItems() {
        System.out.println("\n========== COMPLETE MENU ==========");
        for (MenuItem item : menuItems) {
            item.displayItem();
            System.out.println("-----------------------------------");
        }
    }
    
    // Display items by category
    public void displayByCategory(String category) {
        System.out.println("\n========== " + category.toUpperCase() + " ==========");
        boolean found = false;
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category) && item.isAvailable()) {
                item.displayItem();
                System.out.println("-----------------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items available in this category.");
        }
    }
    
    // Get all menu items
    public ArrayList<MenuItem> getAllItems() {
        return menuItems;
    }
    
    // Get available items only
    public ArrayList<MenuItem> getAvailableItems() {
        ArrayList<MenuItem> availableItems = new ArrayList<MenuItem>();
        for (MenuItem item : menuItems) {
            if (item.isAvailable()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }
}
