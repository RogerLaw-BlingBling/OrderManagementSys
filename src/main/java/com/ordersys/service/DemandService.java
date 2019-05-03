package com.ordersys.service;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.file.fundation.FileContext;
import com.ordersys.model.Demand;
import com.ordersys.repository.DemandRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class DemandService {

    private final DemandRepository demandRepository;
    private final DemandFileService fileService;

    public DemandService(DemandRepository demandRepository, DemandFileService fileService) {
        this.demandRepository = demandRepository;
        this.fileService = fileService;
    }

    public Page<Demand> findAll(Pageable pageable) {
        return demandRepository.findAll(pageable);
    }

    public Optional<Demand> findOne(Integer id) {
        return demandRepository.findById(id);
    }

    public FileContext saveAttachmentTo(Integer id, MultipartFile file) throws IOException {
        Demand demand = demandRepository.findById(id).orElseThrow(() -> new BusinessException("demand_not_found"));

        if (!demand.getFileName().trim().isEmpty()) {
            FileContext oldFile = fileService.get(id, demand.getFileName());
            File backupFile = new File(oldFile.getFile().getParent(), oldFile.getFile().getName() + ".old");
            FileUtils.moveFile(oldFile.getFile(), backupFile);
        }

        FileContext newFile = fileService.save(id, file.getOriginalFilename(), file.getInputStream());
        demand.setFileName(newFile.getDomainPath());
        demandRepository.save(demand);
        return newFile;
    }
}
