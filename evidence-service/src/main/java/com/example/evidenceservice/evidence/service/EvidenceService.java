package com.example.evidenceservice.evidence.service;

import com.example.evidenceservice.evidence.client.CaseClient;
import com.example.evidenceservice.evidence.client.InvestigatorClient;
import com.example.evidenceservice.evidence.dto.*;
import com.example.evidenceservice.evidence.events.EvidenceCreatedEvent;
import com.example.evidenceservice.evidence.events.EvidenceEventProducer;
import com.example.evidenceservice.evidence.mapper.EvidenceMapper;
import com.example.evidenceservice.evidence.model.Evidence;
import com.example.evidenceservice.evidence.repository.EvidenceRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class EvidenceService {
     private final EvidenceRepository evidenceRepository;
     private final CaseClient caseClient;
     private final InvestigatorClient investigatorClient;
     private final EvidenceEventProducer evidenceEventProducer;

     public EvidenceService( EvidenceRepository evidenceRepository, CaseClient caseClient, InvestigatorClient investigatorClient, EvidenceEventProducer evidenceEventProducer){
         this.evidenceRepository = evidenceRepository;
         this.caseClient = caseClient;
         this.investigatorClient = investigatorClient;
         this.evidenceEventProducer = evidenceEventProducer;
     }

     public EvidenceResponseDTO create(EvidenceCreateDTO dto){
         CaseResponse caso;
         InvestigatorResponse investigator_collect;
         InvestigatorResponse investigator_approved;
         try{
             caso = caseClient.getCaseById(dto.caseId());
             log.info("case fetched. case id: {}", caso.id());
         } catch(FeignException.NotFound e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case not found");
         }
         try{
             investigator_collect = investigatorClient.getInvestigatorById(dto.investigatorCollectId());
             log.info("Investigator collect fetched. investigator id: {}, investigator name: {}, case of investigator: {}", investigator_collect.id(), investigator_collect.name(), investigator_collect.caseId());
         } catch(FeignException.NotFound e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Investigator who collects not found");
         }
         try{
             investigator_approved = investigatorClient.getInvestigatorById(dto.investigatorApprovedId());
             log.info("Investigator approved fetched. investigator id: {}, investigator name: {}, case of investigator: {}", investigator_approved.id(), investigator_approved.name(), investigator_approved.caseId());
         } catch(FeignException.NotFound e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Investigator who approved not found");
         }
         if(!investigator_approved.caseId().equals(dto.caseId())){
             throw new ResponseStatusException(HttpStatus.CONFLICT,"Investigator approved case id doesn't match evidence case id");
         }
         if(!investigator_collect.caseId().equals(dto.caseId())){
             throw new ResponseStatusException(HttpStatus.CONFLICT,"Investigator approved case id doesn't match evidence case id");
         }
         Evidence evidence = EvidenceMapper.toEntity(dto);
         Evidence saved = evidenceRepository.save(evidence);

         evidenceEventProducer.publishEvidenceCreated(new EvidenceCreatedEvent(
                 saved.getId(),
                 saved.getTitle(),
                 saved.getCaseId(),
                 LocalDateTime.now()
         ));

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
