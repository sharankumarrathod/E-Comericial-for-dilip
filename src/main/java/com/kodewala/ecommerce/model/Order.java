package com.kodewala.ecommerce.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private int orderId;
    private int customerId;
    private List<CartItem> cartItems;
    private double totalAmount;
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;

    public enum OrderStatus {
        PLACED, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }

    public Order(int orderId, int customerId, List<CartItem> cartItems, double totalAmount, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.cartItems = cartItems;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.orderDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", totalAmount=" + totalAmount +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate.format(formatter) +
                ", itemsCount=" + cartItems.size() +
                '}';
    }
}
