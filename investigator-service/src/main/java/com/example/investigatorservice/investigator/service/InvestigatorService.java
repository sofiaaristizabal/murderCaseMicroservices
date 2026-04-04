package com.example.investigatorservice.investigator.service;

import com.example.investigatorservice.investigator.client.CaseClient;
import com.example.investigatorservice.investigator.dto.*;
import com.example.investigatorservice.investigator.mapper.InvestigatorMapper;
import com.example.investigatorservice.investigator.model.Investigator;
import com.example.investigatorservice.investigator.repository.InvestigatorRepository;
import com.example.investigatorservice.investigator.security.KeycloakAdminService;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InvestigatorService {
    private final InvestigatorRepository investigatorRepository;
    private final CaseClient caseClient;
    private final KeycloakAdminService keycloakAdminService;

    public InvestigatorService(InvestigatorRepository investigatorRepository, CaseClient caseClient, KeycloakAdminService keycloakAdminService){
        this.investigatorRepository = investigatorRepository;
        this.caseClient = caseClient;
        this.keycloakAdminService = keycloakAdminService;
    }

    public InvestigatorResponseDTO create (InvestigatorCreateDTO dto){
        String keycloakId = keycloakAdminService.createUser(
          dto.email(), dto.password(), dto.name(), dto.role()
        );
        Investigator investigator = InvestigatorMapper.toEntity(dto, keycloakId);
        investigatorRepository.save(investigator);
        return InvestigatorMapper.toResponseDTO(investigator);
    }

    public List<InvestigatorResponseDTO> findAll(){
        List<Investigator> investigators = investigatorRepository.findAll();
        return investigators.stream().map(InvestigatorMapper::toResponseDTO).toList();
    }

    public InvestigatorResponseDTO findById(Long id){
        Investigator investigator = investigatorRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "investigator not found with id: " + id));
        return InvestigatorMapper.toResponseDTO(investigator);
    }

    public List<InvestigatorResponseDTO> findInvestigatorsByCase(Long caseId){
        List <Investigator> investigators = investigatorRepository.findByCaseId(caseId);
        return investigators.stream().map(InvestigatorMapper::toResponseDTO).toList();
    }

    public InvestigatorResponseDTO updateById(Long id, InvestigatorUpdateDTO dto){
        Investigator investigator = investigatorRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "investigator not found with id: " + id));
        InvestigatorMapper.updateEntity(investigator, dto);
        Investigator saved = investigatorRepository.save(investigator);
        return InvestigatorMapper.toResponseDTO(saved);
    }

    public InvestigatorResponseDTO assignCase(InvestigatorAssignCaseDTO dto, Long id){
        CaseResponse caso;
        try{
            caso = caseClient.getCaseById(dto.caseId());
        }catch (FeignException.NotFound e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case not found");
        }

        if(caso == null){
          throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "Case not found");
        }
        Investigator investigator = investigatorRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "investigator not found with id: " + id));
        InvestigatorMapper.assignCase(investigator, dto);
        return InvestigatorMapper.toResponseDTO(investigator);
    }

    public void deleteById(Long id){
        Investigator investigator = investigatorRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "investigator not found with id: " + id));
        investigatorRepository.delete(investigator);
    }

    public Investigator getEntityById(Long id){
        return investigatorRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "investigator not found with id: " + id));
    }


}
