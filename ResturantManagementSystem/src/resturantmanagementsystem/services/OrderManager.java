package com.restaurant.services;

import com.restaurant.models.Order;
import com.restaurant.exceptions.*;
import java.util.ArrayList;

// OrderManager class to manage orders using ArrayList
public class OrderManager {
    private static OrderManager instance;
    private ArrayList<Order> orders;
    
    private OrderManager() {
        orders = new ArrayList<Order>();
    }
    
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }
    
    // Add new order
    public void addOrder(Order order) throws InvalidOrderException {
        if (order.getItems().isEmpty()) {
            throw new InvalidOrderException("Cannot place empty order!");
        }
        orders.add(order);
        System.out.println("Order added successfully!");
    }
    
    // Get order by ID
    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }
    
    // Update order status
    public void updateOrderStatus(int orderId, Order.OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            System.out.println("Order status updated to: " + newStatus);
        } else {
            System.out.println("Order not found!");
        }
    }
    
    // Display all orders
    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }
        System.out.println("\n========== ALL ORDERS ==========");
        for (Order order : orders) {
            order.displayOrder();
        }
    }
    
    // Display pending orders
    public void displayPendingOrders() {
        System.out.println("\n========== PENDING ORDERS ==========");
        boolean found = false;
        for (Order order : orders) {
            if (order.getStatus() == Order.OrderStatus.PENDING || 
                order.getStatus() == Order.OrderStatus.PREPARING) {
                order.displayOrder();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pending orders.");
        }
    }
    
    // Get all orders
    public ArrayList<Order> getAllOrders() {
        return orders;
    }
    
    // Calculate total sales
    public double calculateTotalSales() {
        double total = 0.0;
        for (Order order : orders) {
            if (order.getStatus() != Order.OrderStatus.CANCELLED) {
                total += order.getTotalAmount();
            }
        }
        return total;
    }
}
