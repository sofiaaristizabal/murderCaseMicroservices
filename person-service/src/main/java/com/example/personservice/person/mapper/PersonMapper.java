package com.example.personservice.person.mapper;

import com.example.personservice.person.dto.PersonCreateDTO;
import com.example.personservice.person.dto.PersonRespuestaDTO;
import com.example.personservice.person.dto.PersonUpdateDTO;
import com.example.personservice.person.model.Person;

public class PersonMapper {
    private PersonMapper(){}

    public static Person toEntity(PersonCreateDTO dto){
        Person person = new Person();
        person.setName(dto.name());
        person.setTypeDocument(dto.typeDocument());
        person.setDocument(dto.document());
        person.setDate_of_birth(dto.date_of_birth());
        person.setDescription(dto.description());
        person.setAddress(dto.address());
        person.setNationality(dto.nationality());
        person.setContact_info(dto.contact_info());
        person.setCaseId(dto.caseId());
        return person;
    }

    public static PersonRespuestaDTO toRespuestaDTO(Person person){

        return new PersonRespuestaDTO(
                person.getId(),
                person.getName(),
                person.getTypeDocument(),
                person.getDocument(),
                person.getDate_of_birth(),
                person.getDescription(),
                person.getAddress(),
                person.getNationality(),
                person.getContact_info(),
                person.getCaseId()
        );
    }

    public static void updateEntity(Person person, PersonUpdateDTO dto){
        person.setName(dto.name());
        person.setTypeDocument(dto.typeDocument());
        person.setDocument(dto.document());
        person.setDate_of_birth(dto.date_of_birth());
        person.setDescription(dto.description());
        person.setAddress(dto.address());
        person.setNationality(dto.nationality());
        person.setContact_info(dto.contact_info());
        person.setCaseId(dto.caseId());
    }
}
