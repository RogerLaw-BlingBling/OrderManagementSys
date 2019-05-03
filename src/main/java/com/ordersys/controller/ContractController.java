package com.ordersys.controller;

import com.ordersys.commons.RexModel;
import com.ordersys.commons.file.fundation.FileContext;
import com.ordersys.model.Contract;
import com.ordersys.service.ContractService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/contract")
@RequiresUser
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public Page<Contract> get(@PageableDefault Pageable pageable) {
        return contractService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Contract get(@PathVariable("id") Integer id) {
        return contractService.findOne(id).orElse(null);
    }

    @PostMapping("/{id}/attachment")
    public RexModel putAttachment(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        FileContext newFile = contractService.saveAttachmentTo(id, file);
        return new RexModel().withMessage(newFile.getDomainPath());
    }
}
