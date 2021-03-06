package com.ordersys.service;

import com.ordersys.model.Customer;
import com.ordersys.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    public void delete(Integer id) {
        customerRepository.deleteById(id);
    }

    public Page<Customer> queryByCustomerName(String keyword, Pageable pageable) {
        return customerRepository.findByCustomerNameLike("%" + keyword + "%", pageable);
    }
}
