package com.example.caseservice.Case.service;

import com.example.caseservice.Case.dto.CaseCreateDTO;
import com.example.caseservice.Case.dto.CaseResponseDTO;
import com.example.caseservice.Case.dto.CaseUpdateDTO;
import com.example.caseservice.Case.events.CaseCreatedEvent;
import com.example.caseservice.Case.events.CaseEventProducer;
import com.example.caseservice.Case.mapper.CaseMapper;
import com.example.caseservice.Case.model.Case;
import com.example.caseservice.Case.repository.CaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaseService {

    private final CaseRepository caseRepository;
    private final CaseEventProducer caseEventProducer;

    public CaseService(CaseRepository caseRepository, CaseEventProducer caseEventProducer){

        this.caseRepository = caseRepository;
        this.caseEventProducer = caseEventProducer;
    }

    public CaseResponseDTO create(CaseCreateDTO dto){
        Case caso = CaseMapper.toEntity(dto);
        Case saved = caseRepository.save(caso);
        caseEventProducer.publishCaseCreated(new CaseCreatedEvent(
                saved.getId(),
                saved.getTitle(),
                saved.getStatus().name(),
                LocalDateTime.now()
        ));
        return CaseMapper.toResponseDTO(saved);
    }

    public List<CaseResponseDTO> findAll(){
        List<Case> casos = caseRepository.findAll();
        return casos.stream().map(CaseMapper::toResponseDTO).toList();
    }

    public CaseResponseDTO findById(Long id){
        Case caso = caseRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "case not found with id: " + id));
        return CaseMapper.toResponseDTO(caso);
    }

    public void deleteById(Long id){
        Case caso = caseRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "case not found with id: " + id));
        caseRepository.delete(caso);
    }

    public CaseResponseDTO update(Long id, CaseUpdateDTO dto){
        Case caso = caseRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "case not found with id: " + id));
        CaseMapper.updateEntity(caso, dto);
        Case saved = caseRepository.save(caso);
        return CaseMapper.toResponseDTO(saved);
    }

    public Case getEntityById(Long id){
        return caseRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "case not found with id: " + id));
    }
}
