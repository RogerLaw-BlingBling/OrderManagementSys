package com.ordersys.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ordersys.model.Demand;
import com.ordersys.model.Order;
import com.ordersys.model.Project;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandDetailsDto {
    private Demand demand;
    private Order order;
    private Project project;

    public Demand getDemand() {
        return demand;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public DemandDetailsDto() {
    }

    public DemandDetailsDto(Demand demand, Order order, Project project) {
        this.demand = demand;
        this.order = order;
        this.project = project;
    }
}
