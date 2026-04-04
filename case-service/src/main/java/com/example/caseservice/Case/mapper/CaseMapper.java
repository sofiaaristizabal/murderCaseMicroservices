package com.example.caseservice.Case.mapper;

import com.example.caseservice.Case.dto.CaseCreateDTO;
import com.example.caseservice.Case.dto.CaseResponseDTO;
import com.example.caseservice.Case.dto.CaseUpdateDTO;
import com.example.caseservice.Case.model.Case;

public class CaseMapper {

    private CaseMapper(){}

    public static Case toEntity(CaseCreateDTO dto){
        Case caso = new Case();
        caso.setTitle(dto.title());
        caso.setDescription(dto.description());
        caso.setStart_date(dto.start_date());
        caso.setStatus(dto.status());
        caso.setPriority(dto.priority());
        return caso;
    }

    public static CaseResponseDTO toResponseDTO(Case caso){
        return new CaseResponseDTO(
                caso.getId(),
                caso.getTitle(),
                caso.getDescription(),
                caso.getStart_date(),
                caso.getFinish_date(),
                caso.getStatus(),
                caso.getPriority()
        );
    }

    public static void updateEntity(Case caso, CaseUpdateDTO dto){
        caso.setTitle(dto.title());
        caso.setDescription(dto.description());
        caso.setStart_date(dto.start_date());
        caso.setStatus(dto.status());
        caso.setPriority(dto.priority());
    }
}
