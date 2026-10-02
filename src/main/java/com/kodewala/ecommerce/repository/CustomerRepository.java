package com.kodewala.ecommerce.repository;

import com.kodewala.ecommerce.model.Customer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Collection;

public class CustomerRepository {
    private Map<Integer, Customer> customers;

    public CustomerRepository() {
        this.customers = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Optional<Customer> findById(int customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    public Collection<Customer> findAll() {
        return customers.values();
    }

    public boolean updateAddress(int customerId, String newAddress) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            customer.setAddress(newAddress);
            return true;
        }
        return false;
    }

    public boolean exists(int customerId) {
        return customers.containsKey(customerId);
    }

    public int getNextCustomerId() {
        if (customers.isEmpty()) {
            return 1001;
        }
        return customers.keySet().stream().max(Integer::compare).orElse(1000) + 1;
    }
}
