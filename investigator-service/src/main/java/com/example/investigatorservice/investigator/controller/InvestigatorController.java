package com.example.investigatorservice.investigator.controller;

import com.example.investigatorservice.investigator.dto.InvestigatorAssignCaseDTO;
import com.example.investigatorservice.investigator.dto.InvestigatorCreateDTO;
import com.example.investigatorservice.investigator.dto.InvestigatorResponseDTO;
import com.example.investigatorservice.investigator.dto.InvestigatorUpdateDTO;
import com.example.investigatorservice.investigator.mapper.InvestigatorMapper;
import com.example.investigatorservice.investigator.service.InvestigatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investigators")
public class InvestigatorController {

    private InvestigatorService investigatorService;

    public InvestigatorController(InvestigatorService investigatorService){
        this.investigatorService = investigatorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvestigatorResponseDTO create(@Valid @RequestBody InvestigatorCreateDTO dto){
        return investigatorService.create(dto);
    }

    @PutMapping("/assignCase/{id}")
    public InvestigatorResponseDTO assignCase(@PathVariable Long id, @Valid @RequestBody InvestigatorAssignCaseDTO dto){
        return investigatorService.assignCase(dto, id);
    }

    @GetMapping
    public List<InvestigatorResponseDTO> findAll(){
        return investigatorService.findAll();
    }

    @GetMapping("/{id}")
    public InvestigatorResponseDTO findById( @PathVariable Long id){
        return investigatorService.findById(id);
    }

    @GetMapping("/case/{caseId}")
    public List<InvestigatorResponseDTO> findByCaseId(@PathVariable Long caseId){
        return investigatorService.findInvestigatorsByCase(caseId);
    }

    @PutMapping("/{id}")
    public InvestigatorResponseDTO update(@PathVariable Long id, @Valid @RequestBody InvestigatorUpdateDTO dto){
        return investigatorService.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        investigatorService.deleteById(id);
    }
}
