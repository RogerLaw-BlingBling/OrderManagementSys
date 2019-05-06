package com.ordersys;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    public ApplicationConfiguration(ObjectMapper objectMapper) {
//        objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Bean(name = "dataDirectory")
    public File dataDirectory() {
        return new File("./data");
    }


}
