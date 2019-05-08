package com.ordersys.repository;

import com.ordersys.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    Collection<Payment> findAllByOrderId(Integer id);
}
