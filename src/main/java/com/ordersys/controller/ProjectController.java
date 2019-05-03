package com.ordersys.controller;

import com.ordersys.model.Project;
import com.ordersys.service.ProjectService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiresUser
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public Page<Project> get(@PageableDefault Pageable pageable) {
        return projectService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Project get(@PathVariable("id") Integer id) {
        return projectService.findOne(id).orElse(null);
    }

    @GetMapping(params = "in_progress")
    public Page<Project> getInProgress(@PageableDefault(size = 30) Pageable pageable) {
        return projectService.findAllByProgress(Project.Status.IN_PROGRESS, pageable);
    }

    @GetMapping(params = "keyword")
    public Page<Project> queryByName(@RequestParam("keyword") String keyword, @PageableDefault Pageable pageable) {
        return projectService.queryByName(keyword, pageable);
    }
}
