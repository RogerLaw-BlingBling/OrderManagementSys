package com.ordersys.service;

import com.ordersys.model.Order;
import com.ordersys.repository.OrderRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    public Page<Order> query(Order example, Pageable pageable) {
        final ExampleMatcher orderExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("orderId", ExampleMatcher.GenericPropertyMatchers.endsWith().ignoreCase())
//                .withMatcher("orderStatus", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreNullValues()
                .withIgnorePaths("value");

        return orderRepository.findAll(Example.of(example, orderExampleMatcher), pageable);
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
        return orderRepository.findByOrderStatus(status, pageable);
    }
}
