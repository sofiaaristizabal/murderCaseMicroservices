package com.example.investigatorservice;

import org.springframework.boot.SpringApplication;

public class TestInvestigatorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(InvestigatorServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
