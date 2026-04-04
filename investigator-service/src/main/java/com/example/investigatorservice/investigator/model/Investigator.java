package com.example.investigatorservice.investigator.model;

import com.example.investigatorservice.investigator.model.TypeDocument;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Investigators")
@Data
public class Investigator{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private TypeDocument typeDocument;

    @Column(nullable = false)
    private String document;

    @Column(nullable = false)
    private LocalDate date_of_birth;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String keycloakId;

    @Column(nullable = true)
    private Long caseId;


}
