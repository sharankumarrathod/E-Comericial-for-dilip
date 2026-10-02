package com.kodewala.ecommerce.repository;

import com.kodewala.ecommerce.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Optional<Order> findById(int orderId) {
        return orders.stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst();
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    public List<Order> findByCustomerId(int customerId) {
        return orders.stream()
                .filter(o -> o.getCustomerId() == customerId)
                .toList();
    }

    public boolean updateOrderStatus(int orderId, Order.OrderStatus status) {
        return orders.stream()
                .filter(o -> o.getOrderId() == orderId)
                .peek(o -> o.setOrderStatus(status))
                .findFirst()
                .isPresent();
    }

    public int getNextOrderId() {
        if (orders.isEmpty()) {
            return 5001;
        }
        return orders.stream()
                .map(Order::getOrderId)
                .max(Integer::compare)
                .orElse(5000) + 1;
    }
}
