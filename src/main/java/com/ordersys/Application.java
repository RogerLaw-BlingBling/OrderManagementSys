package com.ordersys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class Application {
    @Bean(name = "dataDirectory")
    public File dataDirectory() {
        return new File("./data");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
