package com.example.evidenceservice.evidence.service;

import com.example.evidenceservice.evidence.client.CaseClient;
import com.example.evidenceservice.evidence.client.InvestigatorClient;
import com.example.evidenceservice.evidence.dto.*;
import com.example.evidenceservice.evidence.mapper.EvidenceMapper;
import com.example.evidenceservice.evidence.model.Evidence;
import com.example.evidenceservice.evidence.repository.EvidenceRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EvidenceService {
     private final EvidenceRepository evidenceRepository;
     private final CaseClient caseClient;
     private final InvestigatorClient investigatorClient;

     public EvidenceService( EvidenceRepository evidenceRepository, CaseClient caseClient, InvestigatorClient investigatorClient){
         this.evidenceRepository = evidenceRepository;
         this.caseClient = caseClient;
         this.investigatorClient = investigatorClient;
     }

     public EvidenceResponseDTO create(EvidenceCreateDTO dto){
         CaseResponse caso;
         InvestigatorResponse investigator_collect;
         InvestigatorResponse investigator_approved;
         try{
             caso = caseClient.getCaseById(dto.caseId());
         } catch(FeignException.NotFound e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case not found");
         }
         try{
             investigator_collect = investigatorClient.getInvestigatorById(dto.investigatorCollectId());
         } catch(FeignException.NotFound e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Investigator who collects not found");
         }
         try{
             investigator_approved = investigatorClient.getInvestigatorById(dto.investigatorApprovedId());
         } catch(FeignException.NotFound e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Investigator who approved not found");
         }
         if(caso == null){ throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case doesnt exist");}
         if(investigator_approved == null){ throw new ResponseStatusException(HttpStatus.NOT_FOUND, "investigator who approved doesnt exist");
         }else if(!investigator_approved.caseId().equals(dto.caseId())){
             throw new IllegalArgumentException("Investigator approved case id doesn't match evidence case id");
         }
         if(investigator_collect == null){ throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Investigator who collected doesnt exist");
         }else if(!investigator_collect.caseId().equals(dto.caseId())){
             throw new IllegalArgumentException("Investigator collect case id doesn't match evidence case id");
         }
         Evidence evidence = EvidenceMapper.toEntity(dto);
         Evidence saved = evidenceRepository.save(evidence);
         return EvidenceMapper.toResponseDTO(evidence);
     }

     public List<EvidenceResponseDTO> findAll(){
         List<Evidence> evidences = evidenceRepository.findAll();
         return evidences.stream().map(EvidenceMapper::toResponseDTO).toList();
     }

     public EvidenceResponseDTO findById(Long id){
         Evidence evidence = evidenceRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Evidence not found with id: " + id));
         return EvidenceMapper.toResponseDTO(evidence);
     }

     public List<EvidenceResponseDTO> findByCaseId(Long caseId){
            List<Evidence> evidences = evidenceRepository.findByCaseId(caseId);
            return evidences.stream().map(EvidenceMapper::toResponseDTO).toList();
     }

     public List<EvidenceResponseDTO> findByInvestigatorCollectId(Long invId){
         List<Evidence> evidences = evidenceRepository.findByInvestigatorCollectId(invId);
         return evidences.stream().map(EvidenceMapper::toResponseDTO).toList();
     }

     public List<EvidenceResponseDTO> findByInvestigatorApprovedId(Long invId){
         List<Evidence> evidences = evidenceRepository.findByInvestigatorApprovedId(invId);
         return evidences.stream().map(EvidenceMapper::toResponseDTO).toList();
     }

     public EvidenceResponseDTO updateById(Long id, EvidenceUpdateDTO dto){
         Evidence evidence = evidenceRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Evidence not found with id: " + id));
         EvidenceMapper.updateEntity(evidence, dto);
         Evidence saved = evidenceRepository.save(evidence);
         return EvidenceMapper.toResponseDTO(saved);
     }

     public void deleteById(Long id){
         Evidence evidence = evidenceRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Evidence not found with id: " + id));
         evidenceRepository.delete(evidence);
     }

}
