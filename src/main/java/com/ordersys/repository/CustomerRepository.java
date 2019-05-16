package com.ordersys.repository;

import com.ordersys.model.Customer;
import com.ordersys.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
//    Page<Order> findByTitleLike(String keyword, Pageable pageable);

    Page<Customer> findByCustomerNameLike(String keyword, Pageable pageable);
}
