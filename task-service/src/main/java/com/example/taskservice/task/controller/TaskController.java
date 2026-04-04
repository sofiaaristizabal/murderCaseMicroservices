package com.example.taskservice.task.controller;

import com.example.taskservice.task.dto.TaskCreateDTO;
import com.example.taskservice.task.dto.TaskResponseDTO;
import com.example.taskservice.task.dto.TaskUpdateDTO;
import com.example.taskservice.task.dto.TaskUpdateFinishDTO;
import com.example.taskservice.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@Valid @RequestBody TaskCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<TaskResponseDTO>> findByCaseId(@PathVariable Long caseId) {
        return ResponseEntity.ok(taskService.findByCaseId(caseId));
    }

    @GetMapping("/investigator/{investigatorId}")
    public ResponseEntity<List<TaskResponseDTO>> findByInvestigatorId(@PathVariable Long investigatorId) {
        return ResponseEntity.ok(taskService.findByInvestigatorId(investigatorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO dto) {
        return ResponseEntity.ok(taskService.updateById(id, dto));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponseDTO> complete(@PathVariable Long id, @Valid @RequestBody TaskUpdateFinishDTO dto) {
        return ResponseEntity.ok(taskService.complete(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
