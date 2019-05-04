package com.ordersys.service;

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
import java.util.Collection;
import java.util.Date;
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

    public FileContext saveAttachmentTo(Contract contract, MultipartFile file) throws IOException {
        final Integer id = contract.getId();

        if (!contract.getFilePath().trim().isEmpty()) {
            FileContext oldFile = fileService.get(id, contract.getFilePath());
            File backupFile = new File(oldFile.getFile().getParent(), oldFile.getFile().getName() + ".old");
            FileUtils.moveFile(oldFile.getFile(), backupFile);
        }

        FileContext newFile = fileService.save(id, file.getOriginalFilename(), file.getInputStream());
        contract.setTitle(file.getOriginalFilename());
        contract.setFilePath(newFile.getFile().getName());
        contract.setUploadTime(new Date());
        contractRepository.save(contract);
        return newFile;
    }

    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    public Collection<Contract> findAllByOrderId(String orderId) {
        return contractRepository.findAllByOrderId(orderId);
    }

    public FileContext getAttachmentFile(Contract contract) {
        return fileService.get(contract.getId(),contract.getFilePath());
    }
}
