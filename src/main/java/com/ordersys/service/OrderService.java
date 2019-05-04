package com.ordersys.service;

import com.ordersys.commons.Randoms;
import com.ordersys.model.Order;
import com.ordersys.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Order createNewEmptyOrder() {
        String orderId = Randoms.randomTimeId();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderStatus(Order.Status.CREATED);
        return orderRepository.save(order);
    }

    public Optional<Order> findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Page<Order> queryByStatus(Order.Status status, Pageable pageable) {
        return orderRepository.findByOrderStatus(status,pageable);
    }
}
