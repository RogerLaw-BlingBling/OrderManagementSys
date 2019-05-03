package com.ordersys.service;

import com.ordersys.commons.file.fundation.FileServiceAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
public class ContractFileService extends FileServiceAdapter<Integer> {
    public ContractFileService(@Qualifier("dataDirectory") File rootFolder) {
        super(rootFolder, "contract");
        setFilenameGenerator((origin) -> {
            String extName = origin.substring(origin.lastIndexOf("."), origin.length());
            return UUID.randomUUID().toString() + extName;
        });
    }
}
