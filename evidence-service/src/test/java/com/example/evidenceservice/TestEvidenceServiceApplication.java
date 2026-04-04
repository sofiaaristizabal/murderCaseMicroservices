package com.example.evidenceservice;

import org.springframework.boot.SpringApplication;

public class TestEvidenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(EvidenceServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
