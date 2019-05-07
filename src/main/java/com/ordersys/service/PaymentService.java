package com.ordersys.service;

import com.ordersys.model.Payment;
import com.ordersys.repository.PaymentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }


    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    public Optional<Payment> findOneById(Integer id) {
        return paymentRepository.findById(id);
    }

    public void delete(Integer id) {
        paymentRepository.deleteById(id);
    }
}
