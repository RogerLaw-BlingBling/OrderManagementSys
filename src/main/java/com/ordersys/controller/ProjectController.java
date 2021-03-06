package com.ordersys.controller;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.RexModel;
import com.ordersys.controller.form.ProjectUpdateForm;
import com.ordersys.model.Order;
import com.ordersys.model.Project;
import com.ordersys.service.OrderService;
import com.ordersys.service.ProjectService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final OrderService orderService;

    public ProjectController(ProjectService projectService, OrderService orderService) {
        this.projectService = projectService;
        this.orderService = orderService;
    }

    /**
     *
     * List all projects
     *
     * @param pageable Pageable
     * @return Paged result
     */
    @GetMapping
    @RequiresAuthentication
    public Page<Project> get(@PageableDefault Pageable pageable) {
        return projectService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @RequiresAuthentication
    public Project get(@PathVariable("id") Integer id) {
        return projectService.findOne(id).orElse(null);
    }

    @GetMapping(params = "status")
    @RequiresAuthentication
    public Page<Project> getByStatus(@RequestParam("status") String status, @PageableDefault(size = 30) Pageable pageable) {
        return projectService.findAllByProgress(Project.Status.valueOf(status.toUpperCase()), pageable);
    }

    @GetMapping(params = "keyword")
    @RequiresAuthentication
    public Page<Project> queryByName(@RequestParam("keyword") String keyword, @PageableDefault Pageable pageable) {
        return projectService.queryByName(keyword, pageable);
    }

    @PostMapping(value = "/{id}",params = "status")
    @RequiresAuthentication
    public Project updateStatus(@PathVariable("id") Integer id, @RequestParam("status") String status) {
        Project project = projectService.findOne(id).orElseThrow(() -> new BusinessException("project_not_found"));
        project.setProjectStatus(Project.Status.valueOf(status.toUpperCase()));
        return projectService.save(project);
    }

    @PostMapping(params = "orderId")
    @RequiresAuthentication
    public Project create(@RequestParam("orderId") String orderId, @RequestBody ProjectUpdateForm projectUpdateForm) {
        Order order = orderService.findByOrderId(orderId).orElseThrow(() -> new BusinessException("order_not_found"));

        Project project = new Project();
        project.setOrderId(order.getId());
        project.setCreateTime(new Date());
        project.setProjectStatus(Project.Status.CREATED);

        BeanUtils.copyProperties(projectUpdateForm, project,"status");

        return projectService.save(project);
    }

    @PostMapping("/{id}")
    @RequiresAuthentication
    public Project update(@PathVariable("id") Integer id, @RequestBody ProjectUpdateForm updateForm) {
        Project project = projectService.findOne(id).orElseThrow(() -> new BusinessException("project_not_found"));
        BeanUtils.copyProperties(updateForm, project);
        return projectService.save(project);
    }

    @DeleteMapping("/{id}")
    @RequiresAuthentication
    public RexModel delete(@PathVariable("id") Integer id) {
        projectService.delete(id);
        return new RexModel().withMessage("success");
    }
}
