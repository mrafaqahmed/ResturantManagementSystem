package com.restaurant.ui;

import com.restaurant.models.*;
import com.restaurant.services.*;
import com.restaurant.exceptions.*;
import java.util.Scanner;

// AdminPanel class for admin operations
public class AdminPanel {
    private Scanner scanner;
    private MenuManager menuManager;
    private InventoryManager inventoryManager;
    private OrderManager orderManager;
    private ReportManager reportManager;
    
    public AdminPanel() {
        this.scanner = new Scanner(System.in);
        this.menuManager = MenuManager.getInstance();
        this.inventoryManager = InventoryManager.getInstance();
        this.orderManager = OrderManager.getInstance();
        this.reportManager = ReportManager.getInstance();
    }
    
    public void showAdminMenu() {
        while (true) {
            System.out.println("\n========== ADMIN PANEL ==========");
            System.out.println("1. Manage Menu Items");
            System.out.println("2. Manage Inventory");
            System.out.println("3. View Orders");
            System.out.println("4. View Reports");
            System.out.println("5. Logout");
            System.out.println("================================");
            System.out.print("Enter choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        manageMenu();
                        break;
                    case 2:
                        manageInventory();
                        break;
                    case 3:
                        viewOrders();
                        break;
                    case 4:
                        viewReports();
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
    
    private void manageMenu() {
        System.out.println("\n--- Menu Management ---");
        System.out.println("1. View All Menu Items");
        System.out.println("2. Add Menu Item");
        System.out.println("3. Update Item Price");
        System.out.println("4. Remove Menu Item");
        System.out.println("5. Back");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    menuManager.displayAllItems();
                    break;
                case 2:
                    addMenuItem();
                    break;
                case 3:
                    updateItemPrice();
                    break;
                case 4:
                    removeMenuItem();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
    
    private void addMenuItem() {
        System.out.println("\n--- Add New Menu Item ---");
        System.out.println("Select Category:");
        System.out.println("1. Pizza");
        System.out.println("2. Burger");
        System.out.println("3. Fries");
        System.out.println("4. Drink");
        System.out.println("5. Dessert");
        System.out.print("Enter choice: ");
        
        try {
            int category = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter item ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter item name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter price: ");
            double price = Double.parseDouble(scanner.nextLine());
            
            MenuItem newItem = null;
            
            switch (category) {
                case 1:
                    System.out.print("Enter size (Small/Medium/Large): ");
                    String size = scanner.nextLine();
                    System.out.print("Enter crust type (Thin/Thick): ");
                    String crust = scanner.nextLine();
                    newItem = new Pizza(id, name, price, size, crust);
                    break;
                case 2:
                    System.out.print("Enter type (Grilled/BBQ/Garden): ");
                    String type = scanner.nextLine();
                    System.out.print("Extra cheese? (true/false): ");
                    boolean cheese = Boolean.parseBoolean(scanner.nextLine());
                    newItem = new Burger(id, name, price, type, cheese);
                    break;
                case 3:
                    System.out.print("Enter size (Small/Medium/Large): ");
                    size = scanner.nextLine();
                    newItem = new Fries(id, name, price, size);
                    break;
                case 4:
                    System.out.print("Enter size (Regular/Large): ");
                    size = scanner.nextLine();
                    System.out.print("Is it cold? (true/false): ");
                    boolean cold = Boolean.parseBoolean(scanner.nextLine());
                    newItem = new Drink(id, name, price, size, cold);
                    break;
                case 5:
                    System.out.print("Enter flavor: ");
                    String flavor = scanner.nextLine();
                    newItem = new Dessert(id, name, price, flavor);
                    break;
                default:
                    System.out.println("Invalid category!");
                    return;
            }
            
            if (newItem != null) {
                menuManager.addMenuItem(newItem);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format!");
        }
    }
    
    private void updateItemPrice() {
        System.out.print("Enter item ID to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new price: ");
            double price = Double.parseDouble(scanner.nextLine());
            menuManager.updateItemPrice(id, price);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }
    
    private void removeMenuItem() {
        System.out.print("Enter item ID to remove: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            menuManager.removeMenuItem(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }
    
    private void manageInventory() {
        System.out.println("\n--- Inventory Management ---");
        System.out.println("1. View All Inventory");
        System.out.println("2. View Low Stock Items");
        System.out.println("3. Update Stock");
        System.out.println("4. Back");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    inventoryManager.displayInventory();
                    break;
                case 2:
                    inventoryManager.displayLowStockItems();
                    break;
                case 3:
                    updateStock();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
    
    private void updateStock() {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter quantity to add (negative to reduce): ");
        
        try {
            int quantity = Integer.parseInt(scanner.nextLine());
            inventoryManager.updateStock(itemName, quantity);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity!");
        } catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void viewOrders() {
        System.out.println("\n--- Order Management ---");
        System.out.println("1. View All Orders");
        System.out.println("2. View Pending Orders");
        System.out.println("3. Update Order Status");
        System.out.println("4. Back");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    orderManager.displayAllOrders();
                    break;
                case 2:
                    orderManager.displayPendingOrders();
                    break;
                case 3:
                    updateOrderStatus();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
    
    private void updateOrderStatus() {
        System.out.print("Enter order ID: ");
        try {
            int orderId = Integer.parseInt(scanner.nextLine());
            System.out.println("Select new status:");
            System.out.println("1. PENDING");
            System.out.println("2. PREPARING");
            System.out.println("3. READY");
            System.out.println("4. DELIVERED");
            System.out.println("5. CANCELLED");
            System.out.print("Enter choice: ");
            
            int statusChoice = Integer.parseInt(scanner.nextLine());
            Order.OrderStatus newStatus = null;
            
            switch (statusChoice) {
                case 1: newStatus = Order.OrderStatus.PENDING; break;
                case 2: newStatus = Order.OrderStatus.PREPARING; break;
                case 3: newStatus = Order.OrderStatus.READY; break;
                case 4: newStatus = Order.OrderStatus.DELIVERED; break;
                case 5: newStatus = Order.OrderStatus.CANCELLED; break;
                default:
                    System.out.println("Invalid status!");
                    return;
            }
            
            orderManager.updateOrderStatus(orderId, newStatus);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }
    
    private void viewReports() {
        System.out.println("\n--- Reports ---");
        System.out.println("1. Sales Report");
        System.out.println("2. Inventory Report");
        System.out.println("3. Popular Items Report");
        System.out.println("4. Back");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    reportManager.generateSalesReport();
                    break;
                case 2:
                    reportManager.generateInventoryReport();
                    break;
                case 3:
                    reportManager.generatePopularItemsReport();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
}
