package com.ordersys.controller;

import com.ordersys.commons.RexModel;
import com.ordersys.commons.file.fundation.FileContext;
import com.ordersys.model.Demand;
import com.ordersys.service.DemandService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/demand")
@RequiresUser
public class DemandController {

    private final DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @GetMapping
    public Page<Demand> get(@PageableDefault Pageable pageable) {
        return demandService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Demand get(@PathVariable("id") Integer id) {
        return demandService.findOne(id).orElse(null);
    }

    @PostMapping(value = "/{id}/attachment")
    public RexModel putFile(@PathVariable Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        FileContext fileContext = demandService.saveAttachmentTo(id, file);
        return new RexModel().withMessage(fileContext.getDomainPath());
    }
}
