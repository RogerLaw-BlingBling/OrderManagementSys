package com.ordersys.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Order {
    private Integer id;
    private String orderId;
    private Integer customerId;
    //订单名
    private String title;
    private Integer value;
    //付款次數
    private Integer paymentTimes;
    //乙方经办人
    private Integer handlerId;
    private Date createTime;
    private String comments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    @JsonIgnore
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getPaymentTimes() {
        return paymentTimes;
    }

    public void setPaymentTimes(Integer paymentTimes) {
        this.paymentTimes = paymentTimes;
    }

    //订单总额
    public Double getOrderTotal() {
        return ((double) value) / 100;
    }

    public void setOrderTotal(Double orderTotal) {
        this.value = Math.toIntExact(Math.round(orderTotal * 100));
    }
}
