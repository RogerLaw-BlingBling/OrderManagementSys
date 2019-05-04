package com.ordersys.controller;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.RexModel;
import com.ordersys.commons.file.fundation.FileContext;
import com.ordersys.model.Contract;
import com.ordersys.model.Order;
import com.ordersys.service.ContractService;
import com.ordersys.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/contract")
@RequiresUser
public class ContractController {

    private final ContractService contractService;
    private final OrderService orderService;

    public ContractController(ContractService contractService, OrderService orderService) {
        this.contractService = contractService;
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Contract> get(@PageableDefault Pageable pageable) {
        return contractService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Contract get(@PathVariable("id") Integer id) {
        return contractService.findOne(id).orElse(null);
    }

    @GetMapping("/{id}/file")
    public FileSystemResource downloadFile(@PathVariable("id") Integer id) {
        Contract contract = contractService.findOne(id).orElseThrow(() -> new BusinessException("contract_not_found"));
        return new FileSystemResource(contractService.getAttachmentFile(contract).getFile());
    }

    @GetMapping(params = "orderId")
    public Collection<Contract> queryByOrder(@RequestParam("orderId") String orderId) {
        return contractService.findAllByOrderId(orderId);
    }

    @PostMapping(params = "orderId")
    public RexModel putAttachment(@RequestParam("orderId") String orderId, @RequestParam("file") MultipartFile file) throws IOException {
        Order order = orderService
                .findByOrderId(orderId)
                .orElseThrow(() -> new BusinessException("order_not_found"));

        Contract contract = new Contract();
        contract.setOrderId(order.getId());
        contract = contractService.save(contract);

        FileContext newFile = contractService.saveAttachmentTo(contract, file);
        return new RexModel().withMessage(newFile.getDomainPath());
    }
}
