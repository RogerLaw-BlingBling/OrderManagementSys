package com.ordersys.service;

import com.ordersys.commons.BusinessException;
import com.ordersys.commons.file.fundation.FileContext;
import com.ordersys.controller.DemandController;
import com.ordersys.model.Demand;
import com.ordersys.repository.DemandRepository;
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

    public FileContext saveAttachmentTo(Demand demand, MultipartFile file) throws IOException {
        final Integer id = demand.getId();

        if (!demand.getFileName().trim().isEmpty()) {
            FileContext oldFile = fileService.get(id, demand.getFileName());
            File backupFile = new File(oldFile.getFile().getParent(), oldFile.getFile().getName() + ".old");
            FileUtils.moveFile(oldFile.getFile(), backupFile);
        }

        FileContext newFile = fileService.save(id, file.getOriginalFilename(), file.getInputStream());
        demand.setFileName(newFile.getFile().getName());
        demand.setUploadTime(new Date());
        demand.setTitle(file.getOriginalFilename());
        demandRepository.save(demand);
        return newFile;
    }

    public Demand save(Demand demand) {
        return demandRepository.save(demand);
    }

    public Collection<Demand> findByProjectId(Integer id) {
        return demandRepository.findByProjectId(id);
    }

    public FileContext getAttachment(Demand demand) {
        return fileService.get(demand.getId(),demand.getFileName());
    }
}
