package com.restaurant.ui;

import com.restaurant.models.*;
import com.restaurant.services.*;
import com.restaurant.exceptions.*;
import java.util.Scanner;
import java.util.ArrayList;

// CustomerPanel class for customer operations
public class CustomerPanel {
    private Scanner scanner;
    private MenuManager menuManager;
    private OrderManager orderManager;
    private String customerName;
    private ArrayList<MenuItem> cart;  // Shopping cart using ArrayList
    
    public CustomerPanel(String customerName) {
        this.scanner = new Scanner(System.in);
        this.menuManager = MenuManager.getInstance();
        this.orderManager = OrderManager.getInstance();
        this.customerName = customerName;
        this.cart = new ArrayList<MenuItem>();
    }
    
    public void showCustomerMenu() {
        while (true) {
            System.out.println("\n========== CUSTOMER PANEL ==========");
            System.out.println("Welcome, " + customerName + "!");
            System.out.println("1. Browse Menu");
            System.out.println("2. View Cart");
            System.out.println("3. Place Order");
            System.out.println("4. View My Orders");
            System.out.println("5. Logout");
            System.out.println("===================================");
            System.out.print("Enter choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        browseMenu();
                        break;
                    case 2:
                        viewCart();
                        break;
                    case 3:
                        placeOrder();
                        break;
                    case 4:
                        viewMyOrders();
                        break;
                    case 5:
                        System.out.println("Thank you for visiting! Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
    
    private void browseMenu() {
        System.out.println("\n--- Browse Menu ---");
        System.out.println("1. View All Items");
        System.out.println("2. View by Category");
        System.out.println("3. Add Item to Cart");
        System.out.println("4. Back");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    menuManager.displayAllItems();
                    break;
                case 2:
                    browseByCategory();
                    break;
                case 3:
                    addToCart();
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
    
    private void browseByCategory() {
        System.out.println("\n--- Select Category ---");
        System.out.println("1. Pizza");
        System.out.println("2. Burger");
        System.out.println("3. Fries");
        System.out.println("4. Drink");
        System.out.println("5. Dessert");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            String category = "";
            
            switch (choice) {
                case 1: category = "Pizza"; break;
                case 2: category = "Burger"; break;
                case 3: category = "Fries"; break;
                case 4: category = "Drink"; break;
                case 5: category = "Dessert"; break;
                default:
                    System.out.println("Invalid category!");
                    return;
            }
            
            menuManager.displayByCategory(category);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
    
    private void addToCart() {
        System.out.print("Enter item ID to add to cart: ");
        try {
            int itemId = Integer.parseInt(scanner.nextLine());
            MenuItem item = menuManager.getItemById(itemId);
            
            if (item == null) {
                System.out.println("Item not found!");
                return;
            }
            
            if (!item.isAvailable()) {
                System.out.println("Sorry, this item is currently unavailable.");
                return;
            }
            
            cart.add(item);
            System.out.println("✓ " + item.getName() + " added to cart!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid item ID!");
        }
    }
    
    private void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty!");
            return;
        }
        
        System.out.println("\n========== YOUR CART ==========");
        double total = 0.0;
        int itemNum = 1;
        
        for (MenuItem item : cart) {
            System.out.println(itemNum + ". " + item.getName() + " - Rs." + item.getPrice());
            total += item.getPrice();
            itemNum++;
        }
        
        System.out.println("-------------------------------");
        System.out.println("Total: Rs." + total);
        System.out.println("==============================\n");
        
        System.out.println("1. Remove Item");
        System.out.println("2. Clear Cart");
        System.out.println("3. Back");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    System.out.print("Enter item number to remove: ");
                    int itemToRemove = Integer.parseInt(scanner.nextLine());
                    if (itemToRemove > 0 && itemToRemove <= cart.size()) {
                        MenuItem removed = cart.remove(itemToRemove - 1);
                        System.out.println("Removed: " + removed.getName());
                    } else {
                        System.out.println("Invalid item number!");
                    }
                    break;
                case 2:
                    cart.clear();
                    System.out.println("Cart cleared!");
                    break;
                case 3:
                    return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }
    
    private void placeOrder() {
        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty! Add items first.");
            return;
        }
        
        System.out.println("\n--- Place Order ---");
        System.out.println("Select Order Type:");
        System.out.println("1. Dine-In");
        System.out.println("2. Takeaway");
        System.out.println("3. Delivery");
        System.out.print("Enter choice: ");
        
        try {
            int typeChoice = Integer.parseInt(scanner.nextLine());
            Order.OrderType orderType = null;
            
            switch (typeChoice) {
                case 1: orderType = Order.OrderType.DINE_IN; break;
                case 2: orderType = Order.OrderType.TAKEAWAY; break;
                case 3: orderType = Order.OrderType.DELIVERY; break;
                default:
                    System.out.println("Invalid order type!");
                    return;
            }
            
            // Create order
            Order order = new Order(customerName, orderType);
            
            // Add items from cart
            for (MenuItem item : cart) {
                order.addItem(item);
            }
            
            // Display order summary
            order.displayOrder();
            
            // Process payment
            System.out.println("\n--- Payment ---");
            System.out.println("Total Amount: Rs." + order.getTotalAmount());
            System.out.println("Select Payment Method:");
            System.out.println("1. Cash");
            System.out.println("2. Card");
            System.out.print("Enter choice: ");
            
            int paymentChoice = Integer.parseInt(scanner.nextLine());
            Payment payment = null;
            
            if (paymentChoice == 1) {
                System.out.print("Enter cash amount: ");
                double cashAmount = Double.parseDouble(scanner.nextLine());
                payment = new CashPayment(order.getTotalAmount(), cashAmount);
            } else if (paymentChoice == 2) {
                System.out.print("Enter card number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter cardholder name: ");
                String cardHolder = scanner.nextLine();
                payment = new CardPayment(order.getTotalAmount(), cardNumber, cardHolder);
            } else {
                System.out.println("Invalid payment method!");
                return;
            }
            
            // Process payment
            if (payment.processPayment(order.getTotalAmount())) {
                payment.generateReceipt();
                
                // Place order
                order.placeOrder();
                orderManager.addOrder(order);
                
                // Clear cart
                cart.clear();
                
                System.out.println("\n✓ Order placed successfully!");
                System.out.println("Your order ID is: " + order.getOrderId());
            } else {
                throw new PaymentFailedException("Payment processing failed!");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        } catch (InvalidOrderException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (PaymentFailedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void viewMyOrders() {
        ArrayList<Order> allOrders = orderManager.getAllOrders();
        ArrayList<Order> myOrders = new ArrayList<Order>();
        
        // Filter orders by customer name
        for (Order order : allOrders) {
            if (order.getCustomerName().equals(customerName)) {
                myOrders.add(order);
            }
        }
        
        if (myOrders.isEmpty()) {
            System.out.println("\nYou have no orders yet.");
            return;
        }
        
        System.out.println("\n========== MY ORDERS ==========");
        for (Order order : myOrders) {
            order.displayOrder();
        }
    }
}
