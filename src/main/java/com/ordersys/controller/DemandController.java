package com.ordersys.controller;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.RexModel;
import com.ordersys.commons.file.fundation.FileContext;
import com.ordersys.model.Demand;
import com.ordersys.model.Project;
import com.ordersys.model.dto.DemandDetailsDto;
import com.ordersys.service.DemandService;
import com.ordersys.service.ProjectService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/demand")
@RequiresAuthentication
public class DemandController {

    private final DemandService demandService;
    private final ProjectService projectService;

    public DemandController(DemandService demandService, ProjectService projectService) {
        this.demandService = demandService;
        this.projectService = projectService;
    }

    @GetMapping
    @RequiresAuthentication
    public Page<DemandDetailsDto> get(@PageableDefault Pageable pageable) {
        return demandService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @RequiresAuthentication
    public Demand get(@PathVariable("id") Integer id) {
        return demandService.findOne(id).orElse(null);
    }

    @GetMapping(value = "/{id}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @RequiresAuthentication
    public FileSystemResource getFile(@PathVariable("id") Integer id, HttpServletResponse response) {
        Demand demand = demandService.findOne(id).orElseThrow(() -> new BusinessException("demand_not_found"));
        String filename = UriUtils.encode(demand.getTitle(), "utf8");
        response.setHeader("Content-Description", "attachment; filename=" + filename);
        return new FileSystemResource(demandService.getAttachment(demand).getFile());
    }

    @GetMapping(params = "projectId")
    @RequiresAuthentication
    public Collection<Demand> queryByProjectId(Integer id) {
        return demandService.findByProjectId(id);
    }

    @PostMapping(params = "projectId")
    @RequiresAuthentication
    public RexModel putFile(@RequestParam("projectId") Integer projectId, @RequestParam("file") MultipartFile file) throws IOException {
        Project project = projectService
                .findOne(projectId)
                .orElseThrow(() -> new BusinessException("project_not_found"));

        Demand demand = new Demand();
        demand.setProjectId(project.getId());
        demand = demandService.save(demand);

        FileContext fileContext = demandService.saveAttachmentTo(demand, file);
        return new RexModel().withMessage(fileContext.getDomainPath());
    }

    @DeleteMapping("/{id}")
    @RequiresAuthentication
    public RexModel delete(@PathVariable("id") Integer id) throws IOException {
        Optional<Demand> query = demandService.findOne(id);
        if (!query.isPresent()) return new RexModel().withMessage("demand_not_exists");

        demandService.delete(query.get());
        return new RexModel().withMessage("success");
    }
}
