package com.ordersys.controller;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.RexModel;
import com.ordersys.controller.form.PaymentUpdateForm;
import com.ordersys.model.Order;
import com.ordersys.model.Payment;
import com.ordersys.service.OrderService;
import com.ordersys.service.PaymentService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiresUser
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;

    public PaymentController(PaymentService paymentService, OrderService orderService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Payment> get(@PageableDefault Pageable pageable) {
        return paymentService.findAll(pageable);
    }

    @PostMapping(params = "orderId")
    public Payment pay(@RequestParam("orderId") String orderId, @RequestBody PaymentUpdateForm form) {
        Order order = orderService.findByOrderId(orderId).orElseThrow(() -> new BusinessException("order_not_found"));

        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        BeanUtils.copyProperties(form, payment);
        return paymentService.save(payment);
    }

    @PostMapping("/{id}")
    public Payment update(@PathVariable("id") Integer id, @RequestBody PaymentUpdateForm form) {
        Payment payment = paymentService.findOneById(id).orElseThrow(() -> new BusinessException("payment_not_found"));

        BeanUtils.copyProperties(form, payment);
        return paymentService.save(payment);
    }

    @DeleteMapping("/{id}")
    public RexModel delete(@PathVariable("id") Integer id){
        paymentService.delete(id);
        return new RexModel().withMessage("success");
    }

}
