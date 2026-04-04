package com.example.investigatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InvestigatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestigatorServiceApplication.class, args);
    }

}
