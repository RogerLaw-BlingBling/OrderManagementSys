package com.ordersys.repository;

import com.ordersys.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
