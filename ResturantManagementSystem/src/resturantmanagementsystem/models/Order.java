package com.restaurant.models;

import com.restaurant.interfaces.Orderable;
import java.util.ArrayList;
import java.util.Date;

// Order class implementing Orderable interface - demonstrating interface implementation
public class Order implements Orderable {
    // Static variable to generate unique order IDs
    private static int orderCounter = 1000;
    
    private int orderId;
    private String customerName;
    private ArrayList<MenuItem> items;  // Using ArrayList to store items
    private OrderType orderType;  // Enum usage
    private OrderStatus status;   // Enum usage
    private double totalAmount;
    private Date orderDate;
    
    // Enum for order types - demonstrating enum concept
    public enum OrderType {
        DINE_IN, TAKEAWAY, DELIVERY
    }
    
    // Enum for order status
    public enum OrderStatus {
        PENDING, PREPARING, READY, DELIVERED, CANCELLED
    }
    
    public Order(String customerName, OrderType orderType) {
        this.orderId = ++orderCounter;
        this.customerName = customerName;
        this.orderType = orderType;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.totalAmount = 0.0;
        this.orderDate = new Date();
    }
    
    // Add item to order
    public void addItem(MenuItem item) {
        items.add(item);
        calculateTotal();
    }
    
    // Remove item from order
    public void removeItem(MenuItem item) {
        items.remove(item);
        calculateTotal();
    }
    
    // Calculate total amount
    private void calculateTotal() {
        totalAmount = 0.0;
        for (MenuItem item : items) {
            totalAmount += item.getPrice();
        }
    }
    
    // Implementing interface methods
    @Override
    public void placeOrder() {
        if (items.isEmpty()) {
            System.out.println("Cannot place empty order!");
            return;
        }
        this.status = OrderStatus.PREPARING;
        System.out.println("Order #" + orderId + " placed successfully!");
    }
    
    @Override
    public void cancelOrder() {
        this.status = OrderStatus.CANCELLED;
        System.out.println("Order #" + orderId + " cancelled.");
    }
    
    @Override
    public String getOrderStatus() {
        return status.toString();
    }
    
    // Getters
    public int getOrderId() {
        return orderId;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public ArrayList<MenuItem> getItems() {
        return items;
    }
    
    public OrderType getOrderType() {
        return orderType;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public OrderStatus getStatus() {
        return status;
    }
    
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
    // Display order details
    public void displayOrder() {
        System.out.println("\n========== ORDER #" + orderId + " ==========");
        System.out.println("Customer: " + customerName);
        System.out.println("Type: " + orderType);
        System.out.println("Status: " + status);
        System.out.println("Items:");
        for (MenuItem item : items) {
            System.out.println("  - " + item.getName() + " (Rs." + item.getPrice() + ")");
        }
        System.out.println("Total: Rs." + totalAmount);
        System.out.println("==============================\n");
    }
}
