package com.ordersys.service;

import com.ordersys.commons.file.fundation.FileServiceAdapter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
public class DemandFileService extends FileServiceAdapter<Integer> {
    public DemandFileService(File rootFolder) {
        super(rootFolder, "demand");
        setFilenameGenerator((origin) -> {
            String extName = origin.substring(origin.lastIndexOf("."), origin.length());
            return UUID.randomUUID().toString() + extName;
        });
    }
}
