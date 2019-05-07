package com.ordersys.controller;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.RexModel;
import com.ordersys.controller.form.CustomerUpdateForm;
import com.ordersys.model.Customer;
import com.ordersys.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Page<Customer> findAll(@PageableDefault Pageable pageable) {
        return customerService.findAll(pageable);
    }

    @PostMapping
    public Customer create(@RequestBody CustomerUpdateForm form) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        return customerService.save(customer);
    }

    @PostMapping("/{id}")
    public Customer update(@PathVariable("id") Integer id, @RequestBody CustomerUpdateForm form) {
        Customer customer = customerService.findById(id).orElseThrow(() -> new BusinessException("customer_not_found"));
        BeanUtils.copyProperties(form, customer);
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public RexModel delete(@PathVariable("id") Integer id) {
        customerService.delete(id);
        return new RexModel().withMessage("success");
    }
}
