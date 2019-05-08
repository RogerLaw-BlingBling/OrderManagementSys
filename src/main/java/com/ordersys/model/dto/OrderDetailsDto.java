package com.ordersys.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ordersys.model.Customer;
import com.ordersys.model.Order;
import com.ordersys.model.Payment;
import com.ordersys.model.Project;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailsDto {
    private Order order;
    private Customer customer;
    private Collection<Project> projects;
    private Collection<Payment> payments;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }

    public Collection<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Collection<Payment> payments) {
        this.payments = payments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
