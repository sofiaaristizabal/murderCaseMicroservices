package com.example.personservice.person.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="people")
@Data
public class Person {

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

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String contact_info;

    @Column(nullable = false)
    private Long caseId;
}
