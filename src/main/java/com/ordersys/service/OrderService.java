package com.ordersys.service;

import com.ordersys.model.Order;
import com.ordersys.model.dto.OrderDetailsDto;
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
    private final ProjectService projectService;
    private final PaymentService paymentService;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository,
                        ProjectService projectService,
                        PaymentService paymentService,
                        CustomerService customerService) {

        this.orderRepository = orderRepository;
        this.projectService = projectService;
        this.paymentService = paymentService;
        this.customerService = customerService;
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

    public Optional<Order> findById(Integer id){
        return orderRepository.findById(id);
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

    public Page<Order> queryByTitle(String keyword, Pageable pageable) {
        return orderRepository.findByTitleLike("%" + keyword + "%", pageable);
    }

    public Optional<OrderDetailsDto> findOrderIdByDetails(String id) {
        Optional<Order> queryResult = orderRepository.findByOrderId(id);
        if (!queryResult.isPresent()) return Optional.empty();
        Order order = queryResult.get();

        OrderDetailsDto dto = new OrderDetailsDto();

        dto.setOrder(order);
        if(order.getCustomerId() != null)
            dto.setCustomer(customerService.findById(order.getCustomerId())
                    .orElse(null));

        dto.setProjects(projectService.findAllByOrderId(order));
        dto.setPayments(paymentService.findAllByOrderId(order));

        return Optional.of(dto);
    }
}
