package com.kodewala.ecommerce.service;

import com.kodewala.ecommerce.model.Customer;
import com.kodewala.ecommerce.repository.CustomerRepository;
import com.kodewala.ecommerce.exception.CustomerNotFoundException;
import java.util.Collection;

public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public int registerCustomer(String name, String email, String mobile, String address) {
        int customerId = customerRepository.getNextCustomerId();
        Customer customer = new Customer(customerId, name, email, mobile, address);
        customerRepository.addCustomer(customer);
        return customerId;
    }

    public Customer getCustomerById(int customerId) throws CustomerNotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public Collection<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void updateAddress(int customerId, String newAddress) throws CustomerNotFoundException {
        if (!customerRepository.updateAddress(customerId, newAddress)) {
            throw new CustomerNotFoundException(customerId);
        }
    }

    public boolean customerExists(int customerId) {
        return customerRepository.exists(customerId);
    }
}
