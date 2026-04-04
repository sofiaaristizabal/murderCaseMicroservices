package com.example.caseservice.Case.controller;

import com.example.caseservice.Case.dto.CaseCreateDTO;
import com.example.caseservice.Case.dto.CaseResponseDTO;
import com.example.caseservice.Case.dto.CaseUpdateDTO;
import com.example.caseservice.Case.service.CaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController {
    private final CaseService caseService;
    public CaseController(CaseService caseService){
        this.caseService = caseService;
    }

    @GetMapping
    public List<CaseResponseDTO> getCases(){
        return caseService.findAll();
    }

    @GetMapping("/{id}")
    public CaseResponseDTO getCaseById(@PathVariable Long id){
        return caseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CaseResponseDTO create(@Valid @RequestBody CaseCreateDTO dto){
        return caseService.create(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        caseService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CaseResponseDTO update(@PathVariable Long id, @Valid @RequestBody CaseUpdateDTO dto){
        return caseService.update(id, dto);
    }
}
