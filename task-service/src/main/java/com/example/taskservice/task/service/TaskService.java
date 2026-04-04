package com.example.taskservice.task.service;

import com.example.taskservice.task.client.CaseClient;
import com.example.taskservice.task.client.InvestigatorClient;
import com.example.taskservice.task.dto.*;
import com.example.taskservice.task.mapper.TaskMapper;
import com.example.taskservice.task.model.Status;
import com.example.taskservice.task.model.Task;
import com.example.taskservice.task.repository.TaskRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final CaseClient caseClient;
    private final InvestigatorClient investigatorClient;

    public TaskService(TaskRepository taskRepository, CaseClient caseClient, InvestigatorClient investigatorClient) {
        this.taskRepository = taskRepository;
        this.caseClient = caseClient;
        this.investigatorClient = investigatorClient;
    }

    public TaskResponseDTO create(TaskCreateDTO dto) {

        CaseResponse caso;
        InvestigatorResponse investigator;

        try{
            caso = caseClient.getCaseById(dto.caseId());
        } catch(FeignException.NotFound e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case not found");
        }
        try{
            investigator = investigatorClient.getInvestigatorById(dto.investigatorId());
        } catch(FeignException.NotFound e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Investigator not found");
        }

        if(caso == null){throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case does not exist"); };
        if(investigator == null) {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Investigator does not exist");
        }else if (!investigator.caseId().equals(dto.caseId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Investigator case ID doesnt match task case id ");
        }

        Task task = TaskMapper.toEntity(dto);
        //task.setStatus(Status.INPROGRESS);
        Task saved = taskRepository.save(task);
        return TaskMapper.toResponseDTO(saved);
    }

    public List<TaskResponseDTO> findAll() {
        return taskRepository.findAll()
                .stream().map(TaskMapper::toResponseDTO).toList();
    }

    public TaskResponseDTO findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found with id: " + id));
        return TaskMapper.toResponseDTO(task);
    }

    public List<TaskResponseDTO> findByCaseId(Long caseId) {
        return taskRepository.findByCaseId(caseId)
                .stream().map(TaskMapper::toResponseDTO).toList();
    }

    public List<TaskResponseDTO> findByInvestigatorId(Long investigatorId) {
        return taskRepository.findByInvestigatorId(investigatorId)
                .stream().map(TaskMapper::toResponseDTO).toList();
    }

    public TaskResponseDTO updateById(Long id, TaskUpdateDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found with id: " + id));
        TaskMapper.updateEntity(task, dto);
        Task saved = taskRepository.save(task);
        return TaskMapper.toResponseDTO(saved);
    }

    public TaskResponseDTO complete(Long id, TaskUpdateFinishDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found with id: " + id));
        task.setFinish_date(dto.finish_date());
        task.setStatus(Status.FINISHED);
        Task saved = taskRepository.save(task);
        return TaskMapper.toResponseDTO(saved);
    }

    public void deleteById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found with id: " + id));
        taskRepository.delete(task);
    }
}
