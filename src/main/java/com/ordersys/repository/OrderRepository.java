package com.ordersys.repository;

import com.ordersys.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    Optional<Order> findByOrderId(String orderId);

    Page<Order> findByOrderStatus(Order.Status status, Pageable pageable);
}
