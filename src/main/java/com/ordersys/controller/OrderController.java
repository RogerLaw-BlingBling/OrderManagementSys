package com.ordersys.controller;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.Randoms;
import com.ordersys.controller.form.OrderUpdateForm;
import com.ordersys.model.Order;
import com.ordersys.model.dto.OrderDetailsDto;
import com.ordersys.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    @RequiresAuthentication
    public OrderDetailsDto get(@PathVariable("id") String id) {
        return orderService.findOrderIdByDetails(id).orElseThrow(() -> new BusinessException("order_not_found"));
    }

    @GetMapping
    @RequiresAuthentication
    public Page<Order> get(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                           @RequestParam(value = "status", required = false, defaultValue = "") String status,
                           @PageableDefault Pageable pageable) {

        if (keyword.isEmpty() && status.isEmpty()) return orderService.findAll(pageable);

        Order orderExample = new Order();
        if (!keyword.isEmpty()) orderExample.setTitle(keyword);
        if (!status.isEmpty()) orderExample.setOrderStatus(Order.Status.valueOf(status.toUpperCase()));

        return orderService.query(orderExample, pageable);
    }

//    @GetMapping(params = "status")
//    public Page<Order> queryByStatus(@RequestParam("status") String status, @PageableDefault Pageable pageable) {
//        return orderService.queryByStatus(Order.Status.valueOf(status.toUpperCase()), pageable);
//    }

//    @PostMapping("/{id}")
//    public Order update(@PathVariable("id") Integer id,@RequestBody OrderUpdateForm form){
//        Order order = orderService.findById(id).orElseThrow(()->new BusinessException("order_not_found"));
//        BeanUtils.copyProperties(form,order);
//        return orderService.save(order);
//    }

    @PostMapping
    @RequiresAuthentication
    public Order create(@RequestBody OrderUpdateForm form) {
        Order order = new Order();
        order.setOrderId(Randoms.randomTimeId());
        order.setOrderStatus(Order.Status.CREATED);
        order.setCreateTime(new Date());
        BeanUtils.copyProperties(form, order);
        return orderService.save(order);
    }

    @PostMapping(value = "/{orderId}", params = "status")
    @RequiresAuthentication
    public Order updateStatus(@PathVariable("orderId") String orderId, @RequestParam("status") String status) {

        Order order = orderService
                .findByOrderId(orderId)
                .orElseThrow(() -> new BusinessException("order_not_found"));

        order.setOrderStatus(Order.Status.valueOf(status.toUpperCase()));
        return orderService.save(order);
    }

//    @GetMapping(params = "keyword")
//    public Page<Order> queryByTitle(@RequestParam("keyword") String keyword,Pageable pageable){
//        return orderService.queryByTitle(keyword,pageable);
//    }

    @PostMapping("/{orderId}")
    @RequiresAuthentication
    public Order save(@PathVariable("orderId") String orderId, @RequestBody OrderUpdateForm form) {
        Order order = orderService
                .findByOrderId(orderId)
                .orElseThrow(() -> new BusinessException("order_not_found"));

        switch (order.getOrderStatus()) {
            case CREATED:
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
