package com.kodewala.ecommerce.service;

import com.kodewala.ecommerce.model.CartItem;
import com.kodewala.ecommerce.model.Order;
import com.kodewala.ecommerce.repository.OrderRepository;
import com.kodewala.ecommerce.exception.InvalidOrderException;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(int customerId, List<CartItem> cartItems, double totalAmount) throws InvalidOrderException {
        if (cartItems == null || cartItems.isEmpty()) {
            throw new InvalidOrderException("Cannot place order with empty cart");
        }

        int orderId = orderRepository.getNextOrderId();
        Order order = new Order(orderId, customerId, new java.util.ArrayList<>(cartItems), totalAmount, Order.OrderStatus.PLACED);
        orderRepository.addOrder(order);
        return order;
    }

    public Order getOrderById(int orderId) throws InvalidOrderException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new InvalidOrderException("Order with ID " + orderId + " does not exist."));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getCustomerOrders(int customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public void updateOrderStatus(int orderId, Order.OrderStatus status) throws InvalidOrderException {
        if (!orderRepository.updateOrderStatus(orderId, status)) {
            throw new InvalidOrderException("Order with ID " + orderId + " does not exist.");
        }
    }

    public void cancelOrder(int orderId) throws InvalidOrderException {
        Order order = getOrderById(orderId);
        if (order.getOrderStatus() == Order.OrderStatus.DELIVERED || 
            order.getOrderStatus() == Order.OrderStatus.CANCELLED) {
            throw new InvalidOrderException("Cannot cancel order with status: " + order.getOrderStatus());
        }
        updateOrderStatus(orderId, Order.OrderStatus.CANCELLED);
    }
}
