package com.ordersys.controller;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.RexModel;
import com.ordersys.controller.form.OrderUpdateForm;
import com.ordersys.model.Order;
import com.ordersys.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiresUser
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Order> get(@PageableDefault Pageable pageable) {
        return orderService.findAll(pageable);
    }

    @GetMapping(params = "status")
    public Page<Order> queryByStatus(@RequestParam("status") Order.Status status, @PageableDefault Pageable pageable) {
        return orderService.queryByStatus(status, pageable);
    }

    @PutMapping
    public RexModel<String> create() {
        Order order = orderService.createNewEmptyOrder();
        return new RexModel<String>()
                .withData(order.getOrderId());
    }

    @PostMapping(value = "/{orderId}", params = "status")
    public Order updateStatus(@PathVariable("orderId") String orderId, @RequestParam("status") Order.Status status) {
        Order order = orderService
                .findByOrderId(orderId)
                .orElseThrow(() -> new BusinessException("order_not_found"));

        order.setOrderStatus(status);
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public Order save(@PathVariable("id") String orderId, @RequestBody OrderUpdateForm form) {
        Order order = orderService
                .findByOrderId(orderId)
                .orElseThrow(() -> new BusinessException("order_not_found"));

        switch (order.getOrderStatus()) {
            case CREATED:
                // Allow all things to be overwrite
                BeanUtils.copyProperties(form, order);
                break;
            case IN_PROGRESS:
                // Only allow some properties to be overwrite
                BeanUtils.copyProperties(form, order, "customerId");
                break;
            case FINISHED:
                throw new BusinessException("order_finished_update_not_allowed");
        }

        return orderService.save(order);
    }
}
