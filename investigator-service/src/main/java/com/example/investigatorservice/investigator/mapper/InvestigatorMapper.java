package com.example.investigatorservice.investigator.mapper;

import com.example.investigatorservice.investigator.dto.InvestigatorAssignCaseDTO;
import com.example.investigatorservice.investigator.dto.InvestigatorCreateDTO;
import com.example.investigatorservice.investigator.dto.InvestigatorResponseDTO;
import com.example.investigatorservice.investigator.dto.InvestigatorUpdateDTO;
import com.example.investigatorservice.investigator.model.Investigator;

public class InvestigatorMapper {
    private InvestigatorMapper(){}

    public static Investigator toEntity(InvestigatorCreateDTO dto, String keycloakId){
        Investigator investigator = new Investigator();
        investigator.setName(dto.name());
        investigator.setTypeDocument(dto.typeDocument());
        investigator.setDocument(dto.document());
        investigator.setDate_of_birth(dto.date_of_birth());
        investigator.setEmail(dto.email());
        investigator.setKeycloakId(keycloakId);
        return investigator;
    }

    public static InvestigatorResponseDTO toResponseDTO(Investigator investigator){

        return new InvestigatorResponseDTO(
                investigator.getId(),
                investigator.getName(),
                investigator.getTypeDocument(),
                investigator.getDocument(),
                investigator.getDate_of_birth(),
                investigator.getEmail(),
                investigator.getKeycloakId(),
                investigator.getCaseId()
        );
    }

    public static void updateEntity(Investigator investigator, InvestigatorUpdateDTO dto){
        investigator.setName(dto.name());
        investigator.setTypeDocument(dto.typeDocument());
        investigator.setDocument(dto.document());
        investigator.setDate_of_birth(dto.date_of_birth());
    }

    public static void assignCase(Investigator investigator, InvestigatorAssignCaseDTO dto){
        investigator.setCaseId(dto.caseId());
    }
}
