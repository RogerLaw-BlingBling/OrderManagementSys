package com.ordersys.controller.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ordersys.model.Order;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderUpdateForm {

    private Integer customerId;
    private String title;
    private Double orderTotal;
    private Integer paymentTimes;
    private String handlerName;
    private Order.Status orderStatus;
    private String comments;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getPaymentTimes() {
        return paymentTimes;
    }

    public void setPaymentTimes(Integer paymentTimes) {
        this.paymentTimes = paymentTimes;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Order.Status getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Order.Status orderStatus) {
        this.orderStatus = orderStatus;
    }
}
