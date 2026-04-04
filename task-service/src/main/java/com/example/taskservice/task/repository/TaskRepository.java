package com.example.taskservice.task.repository;

import com.example.taskservice.task.model.Task;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCaseId(Long caseId);
    List<Task> findByInvestigatorId(Long investigatorId);

}
