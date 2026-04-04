package com.example.evidenceservice.evidence.controller;

import com.example.evidenceservice.evidence.dto.EvidenceCreateDTO;
import com.example.evidenceservice.evidence.dto.EvidenceResponseDTO;
import com.example.evidenceservice.evidence.dto.EvidenceUpdateDTO;
import com.example.evidenceservice.evidence.service.EvidenceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/evidences")
public class EvidenceController {
    private EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService){
        this.evidenceService = evidenceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EvidenceResponseDTO create(@Valid @RequestBody EvidenceCreateDTO dto){
        return evidenceService.create(dto);
    }

    @GetMapping
    public List<EvidenceResponseDTO> findAll(){
        return evidenceService.findAll();
    }

    @GetMapping("/{id}")
    public EvidenceResponseDTO findById(@PathVariable Long id){
        return evidenceService.findById(id);
    }

    @GetMapping("/case/{caseId}")
    public List<EvidenceResponseDTO> findByCaseId(@PathVariable Long caseId){
        return evidenceService.findByCaseId(caseId);
    }

    @GetMapping("/investigatorCollect/{invCollectId}")
    public List<EvidenceResponseDTO> findByInvestigatorCollectId(@PathVariable Long invCollectId){
        return evidenceService.findByInvestigatorCollectId(invCollectId);
    }

    @GetMapping("/investigatorApproved/{invApprovedId}")
    public List<EvidenceResponseDTO> findByInvestigatorApprovedId(@PathVariable Long invApprovedId){
        return evidenceService.findByInvestigatorApprovedId(invApprovedId);
    }

    @PutMapping("/{id}")
    public EvidenceResponseDTO update(@PathVariable Long id, @Valid @RequestBody EvidenceUpdateDTO dto){
        return evidenceService.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        evidenceService.deleteById(id);
    }
 }
