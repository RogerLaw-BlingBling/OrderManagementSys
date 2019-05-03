package com.ordersys.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {
    public enum Status {
        CREATE, IN_PROGRESS, FINISHED
    }

    private Integer id;
    private Integer orderId;
    private String projectName;
    private Status projectStatus;
    private Date createTime;
    private Date beginTime;
    private Date endTime;
    private float duration;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(16)")
    public Status getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Status projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(precision = 8, scale = 2)
    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

}
