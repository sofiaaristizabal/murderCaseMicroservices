package com.example.evidenceservice.evidence.mapper;

import com.example.evidenceservice.evidence.dto.EvidenceCreateDTO;
import com.example.evidenceservice.evidence.dto.EvidenceResponseDTO;
import com.example.evidenceservice.evidence.dto.EvidenceUpdateDTO;
import com.example.evidenceservice.evidence.model.Evidence;

public class EvidenceMapper {
    private EvidenceMapper(){}

    public static Evidence toEntity(EvidenceCreateDTO dto){
        Evidence evidence = new Evidence();
        evidence.setWrittenEvidence(dto.writtenEvidence());
        evidence.setContext(dto.context());
        evidence.setTitle(dto.title());
        evidence.setPlaceCollect(dto.placeCollect());
        evidence.setCaseId(dto.caseId());
        evidence.setInvestigatorCollectId(dto.investigatorCollectId());
        evidence.setInvestigatorApprovedId(dto.investigatorApprovedId());
        return evidence;
    }

    public static EvidenceResponseDTO toResponseDTO( Evidence evidence){
        return new EvidenceResponseDTO(
                evidence.getId(),
                evidence.getWrittenEvidence(),
                evidence.getContext(),
                evidence.getTitle(),
                evidence.getPlaceCollect(),
                evidence.getCaseId(),
                evidence.getInvestigatorCollectId(),
                evidence.getInvestigatorApprovedId()

        );
    }

    public static void updateEntity(Evidence evidence, EvidenceUpdateDTO dto){
        evidence.setWrittenEvidence(dto.writtenEvidence());
        evidence.setContext(dto.context());
        evidence.setTitle(dto.title());
        evidence.setPlaceCollect(dto.placeCollect());
        evidence.setCaseId(dto.caseId());
        evidence.setInvestigatorCollectId(dto.investigatorCollectId());
        evidence.setInvestigatorApprovedId(dto.investigatorApprovedId());
    }

}
