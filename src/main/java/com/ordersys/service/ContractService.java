package com.ordersys.service;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.file.fundation.FileContext;
import com.ordersys.model.Contract;
import com.ordersys.repository.ContractRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractFileService fileService;

    public ContractService(ContractRepository contractRepository, ContractFileService fileService) {
        this.contractRepository = contractRepository;
        this.fileService = fileService;
    }

    public Page<Contract> findAll(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    public Optional<Contract> findOne(Integer id) {
        return contractRepository.findById(id);
    }

    public FileContext saveAttachmentTo(Integer id, MultipartFile file) throws IOException {
        Contract contract = contractRepository.findById(id).orElseThrow(() -> new BusinessException("demand_not_found"));

        if (!contract.getFilePath().trim().isEmpty()) {
            FileContext oldFile = fileService.get(id, contract.getFilePath());
            File backupFile = new File(oldFile.getFile().getParent(), oldFile.getFile().getName() + ".old");
            FileUtils.moveFile(oldFile.getFile(), backupFile);
        }

        FileContext newFile = fileService.save(id, file.getOriginalFilename(), file.getInputStream());
        contract.setFilePath(newFile.getDomainPath());
        contractRepository.save(contract);
        return newFile;
    }
}
