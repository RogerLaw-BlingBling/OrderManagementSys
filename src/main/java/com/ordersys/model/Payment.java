package com.ordersys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_payment")
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
    //附加说明
    private String addition;
    //摘要
    private String summary;

    private Integer value = 0;

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
    @Transient
    public Double getAmount() {
        return value == null ? 0 : ((double) value) / 100;
    }

    public void setAmount(Double amount) {
        this.value = amount == null ? this.value : Math.toIntExact(Math.round(amount * 100));
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
