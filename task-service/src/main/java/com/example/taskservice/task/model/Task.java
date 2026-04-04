package com.example.taskservice.task.model;

import com.example.taskservice.task.model.Priority;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Priority priority;

    @Column(nullable = false)
    private LocalDate start_date;

    @Column(nullable = true)
    private LocalDate finish_date;

    @Column(nullable = false)
    private Long investigatorId;

    @Column(nullable = false)
    private Long caseId;

}
