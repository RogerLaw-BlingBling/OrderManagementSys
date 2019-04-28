package com.ordersys.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Order {
    private Integer id;
    private String orderId;
    private Integer customerId;
    //订单名
    private String Title;
    //订单总额
    private int oderTotal;
    //预计金额
    private String estimatedAmount;
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
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getOderTotal() {
        return oderTotal;
    }

    public void setOderTotal(int oderTotal) {
        this.oderTotal = oderTotal;
    }

    public String getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(String estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
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
}
