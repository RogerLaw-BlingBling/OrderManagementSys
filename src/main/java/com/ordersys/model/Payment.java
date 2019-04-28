package com.ordersys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment {
    private Integer id;
    private Integer orderId;
    //付款方式
    private String paymentTerm;
    //汇款人
    private String payer;
    //收款人
    private String payee;
    //收款时间
    private Date paymentTime;

    private Integer value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    @JsonIgnore
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    // The real amount of payment
    public Double getAmount() {
        return ((double) value) / 100;
    }

    public void setAmount(Double amount) {
        this.value = Math.toIntExact(Math.round(amount * 100));
    }
}
