package com.restaurant.services;

import com.restaurant.models.Order;
import com.restaurant.models.MenuItem;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

// ReportManager for generating reports
public class ReportManager {
    private static ReportManager instance;
    
    private ReportManager() {
    }
    
    public static ReportManager getInstance() {
        if (instance == null) {
            instance = new ReportManager();
        }
        return instance;
    }
    
    // Generate sales report
    public void generateSalesReport() {
        OrderManager orderManager = OrderManager.getInstance();
        ArrayList<Order> orders = orderManager.getAllOrders();
        
        System.out.println("\n========== SALES REPORT ==========");
        System.out.println("Date: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        System.out.println("----------------------------------");
        
        if (orders.isEmpty()) {
            System.out.println("No sales data available.");
            System.out.println("=================================\n");
            return;
        }
        
        int totalOrders = orders.size();
        int completedOrders = 0;
        int cancelledOrders = 0;
        double totalRevenue = 0.0;
        
        for (Order order : orders) {
            if (order.getStatus() == Order.OrderStatus.DELIVERED) {
                completedOrders++;
                totalRevenue += order.getTotalAmount();
            } else if (order.getStatus() == Order.OrderStatus.CANCELLED) {
                cancelledOrders++;
            }
        }
        
        System.out.println("Total Orders: " + totalOrders);
        System.out.println("Completed Orders: " + completedOrders);
        System.out.println("Cancelled Orders: " + cancelledOrders);
        System.out.println("Pending/In-Progress: " + (totalOrders - completedOrders - cancelledOrders));
        System.out.println("----------------------------------");
        System.out.println("Total Revenue: Rs." + totalRevenue);
        System.out.println("Average Order Value: Rs." + 
                         (completedOrders > 0 ? (totalRevenue / completedOrders) : 0));
        System.out.println("=================================\n");
    }
    
    // Generate inventory report
    public void generateInventoryReport() {
        InventoryManager inventoryManager = InventoryManager.getInstance();
        
        System.out.println("\n========== INVENTORY REPORT ==========");
        System.out.println("Date: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
        System.out.println("-------------------------------------");
        
        inventoryManager.displayInventory();
        inventoryManager.displayLowStockItems();
    }
    
    // Generate popular items report
    public void generatePopularItemsReport() {
        OrderManager orderManager = OrderManager.getInstance();
        ArrayList<Order> orders = orderManager.getAllOrders();
        
        System.out.println("\n========== POPULAR ITEMS REPORT ==========");
        
        if (orders.isEmpty()) {
            System.out.println("No order data available.");
            System.out.println("=========================================\n");
            return;
        }
        
        // Simple count of items ordered
        ArrayList<String> itemNames = new ArrayList<String>();
        ArrayList<Integer> itemCounts = new ArrayList<Integer>();
        
        for (Order order : orders) {
            if (order.getStatus() != Order.OrderStatus.CANCELLED) {
                for (MenuItem item : order.getItems()) {
                    int index = itemNames.indexOf(item.getName());
                    if (index >= 0) {
                        itemCounts.set(index, itemCounts.get(index) + 1);
                    } else {
                        itemNames.add(item.getName());
                        itemCounts.add(1);
                    }
                }
            }
        }
        
        // Display results
        for (int i = 0; i < itemNames.size(); i++) {
            System.out.println(itemNames.get(i) + ": " + itemCounts.get(i) + " orders");
        }
        System.out.println("=========================================\n");
    }
}
