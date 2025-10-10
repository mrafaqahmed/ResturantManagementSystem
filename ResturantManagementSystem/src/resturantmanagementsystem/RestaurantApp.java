package com.restaurant;

import com.restaurant.models.*;
import com.restaurant.ui.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main Restaurant Management System Application
 * Demonstrates all Java concepts: OOP, Inheritance, Polymorphism, Interfaces,
 * Exception Handling, ArrayLists, and more
 * 
 * @author Restaurant Management Team
 * @version 1.0
 */
public class RestaurantApp {
    // Static ArrayList to store users - demonstrating static variable and ArrayList
    private static ArrayList<User> users = new ArrayList<User>();
    private static Scanner scanner = new Scanner(System.in);
    
    // Static block - runs when class is loaded
    static {
        // Initialize default users
        users.add(new Admin("admin", "admin123"));
        users.add(new Customer("customer1", "pass123"));
        users.add(new Customer("john", "john123"));
    }
    
    /**
     * Main method - entry point of application
     * Demonstrates command-line arguments handling
     */
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   RESTAURANT MANAGEMENT SYSTEM (CLI)      ║");
        System.out.println("║   Java Console Application                ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        
        // Check command line arguments
        if (args.length > 0) {
            System.out.println("Command-line argument received: " + args[0]);
        }
        
        // Main application loop
        while (true) {
            showMainMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        System.out.println("\nThank you for using our Restaurant Management System!");
                        System.out.println("Goodbye! 👋");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                // Exception handling - demonstrating try-catch block
                System.out.println("Error: Please enter a valid number!");
            } catch (Exception e) {
                // Catching general exceptions
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
    
    /**
     * Display main menu
     */
    private static void showMainMenu() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║           MAIN MENU                   ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║  1. Login                             ║");
        System.out.println("║  2. Register as Customer              ║");
        System.out.println("║  3. Exit                              ║");
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Login method - demonstrates user authentication
     */
    private static void login() {
        System.out.println("\n========== LOGIN ==========");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        // Search for user using enhanced for loop
        User authenticatedUser = null;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                authenticatedUser = user;
                break;
            }
        }
        
        if (authenticatedUser != null) {
            System.out.println("\n✓ Login successful!");
            authenticatedUser.displayDashboard();
            
            // Polymorphism - different behavior based on user type
            if (authenticatedUser instanceof Admin) {
                // Admin user - show admin panel
                AdminPanel adminPanel = new AdminPanel();
                adminPanel.showAdminMenu();
            } else if (authenticatedUser instanceof Customer) {
                // Customer user - show customer panel
                CustomerPanel customerPanel = new CustomerPanel(authenticatedUser.getUsername());
                customerPanel.showCustomerMenu();
            }
        } else {
            System.out.println("\n✗ Invalid username or password!");
        }
    }
    
    /**
     * Register new customer - demonstrates object creation
     */
    private static void register() {
        System.out.println("\n========== CUSTOMER REGISTRATION ==========");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists! Please choose a different one.");
                return;
            }
        }
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Create new customer object
        Customer newCustomer = new Customer(username, password);
        users.add(newCustomer);
        
        System.out.println("\n✓ Registration successful!");
        System.out.println("You can now login with your credentials.");
    }
    
    /**
     * Display all registered users (for testing)
     * Demonstrates final keyword
     */
    public static final void displayAllUsers() {
        System.out.println("\n========== REGISTERED USERS ==========");
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + " | Role: " + user.getRole());
        }
        System.out.println("=====================================\n");
    }
}
