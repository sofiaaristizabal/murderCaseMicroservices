package com.example.taskservice.task.mapper;

import com.example.taskservice.task.dto.TaskCreateDTO;
import com.example.taskservice.task.dto.TaskResponseDTO;
import com.example.taskservice.task.dto.TaskUpdateDTO;
import com.example.taskservice.task.model.Task;

public class TaskMapper {
    private TaskMapper(){}

    public static Task toEntity(TaskCreateDTO dto){
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setPriority(dto.priority());
        task.setStart_date(dto.start_date());
        task.setCaseId(dto.caseId());
        task.setInvestigatorId(dto.investigatorId());
        return task;
    }

    public static TaskResponseDTO toResponseDTO(Task task){

        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getStart_date(),
                task.getFinish_date(),
                task.getInvestigatorId(),
                task.getCaseId()
        );
    }

    public static void updateEntity(Task task, TaskUpdateDTO dto){
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setPriority(dto.priority());
        task.setStart_date(dto.start_date());
        task.setCaseId(dto.caseId());
        task.setInvestigatorId(dto.investigatorId());
    }
}
