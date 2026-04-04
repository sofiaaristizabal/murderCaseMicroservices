package com.example.evidenceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EvidenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvidenceServiceApplication.class, args);
    }

}
