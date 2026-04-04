package com.example.caseservice.Case.model;


import com.example.caseservice.Case.model.Priority;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="cases")
@Data
public class Case {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=120)
    private String title;

    @Column(nullable = false, length=120)
    private String description;

    @Column(nullable = false)
    private LocalDate start_date;

    @Column()
    private LocalDate finish_date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

}
