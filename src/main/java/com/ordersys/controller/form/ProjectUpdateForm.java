package com.ordersys.controller.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ordersys.model.Project;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectUpdateForm {
    private Project.Status projectStatus;
    private String projectName;
    private Date beginTime;
    private Date endTime;
    private Double duration;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Project.Status getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Project.Status projectStatus) {
        this.projectStatus = projectStatus;
    }
}
