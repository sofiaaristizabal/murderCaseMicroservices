package com.example.evidenceservice.evidence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Evidences")
@Data
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=1000)
    private String writtenEvidence;

    // @Column(nullable = true) para usar fotos deberia usar mongo
    //private String photo;

    @Column(nullable = false)
    private String context;

    @Column(nullable = false, length=100)
    private String title;

    @Column(nullable = false)
    private String placeCollect;

    @Column(nullable = false)
    private Long caseId;

    @Column(nullable = false)
    private Long investigatorCollectId;

    @Column(nullable = false)
    private Long investigatorApprovedId;


}
