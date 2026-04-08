package com.example.personservice.person.service;

import com.example.personservice.person.client.CaseClient;
import com.example.personservice.person.dto.CaseResponse;
import com.example.personservice.person.dto.PersonCreateDTO;
import com.example.personservice.person.dto.PersonRespuestaDTO;
import com.example.personservice.person.dto.PersonUpdateDTO;
import com.example.personservice.person.mapper.PersonMapper;
import com.example.personservice.person.model.Person;
import com.example.personservice.person.repository.PersonRepository;
import feign.FeignException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final CaseClient caseClient;

    public PersonService(PersonRepository personRepository, CaseClient caseClient) {
        this.personRepository = personRepository;
        this.caseClient = caseClient;
    }

    public PersonRespuestaDTO create(PersonCreateDTO dto) {
        CaseResponse caso;
        try{
            caso = caseClient.getCaseById(dto.caseId());
        }catch(FeignException.NotFound e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case not found");
        }

        Person person = PersonMapper.toEntity(dto);
        Person saved = personRepository.save(person);
        return PersonMapper.toRespuestaDTO(saved);
    }

    public List<PersonRespuestaDTO> findAll() {
        return personRepository.findAll()
                .stream().map(PersonMapper::toRespuestaDTO).toList();
    }

    public PersonRespuestaDTO findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found with id: " + id));
        return PersonMapper.toRespuestaDTO(person);
    }

    public List<PersonRespuestaDTO> findByCaseId(Long caseId) {
        return personRepository.findByCaseId(caseId)
                .stream().map(PersonMapper::toRespuestaDTO).toList();
    }

    public PersonRespuestaDTO updateById(Long id, PersonUpdateDTO dto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found with id: " + id));
        PersonMapper.updateEntity(person, dto);
        Person saved = personRepository.save(person);
        return PersonMapper.toRespuestaDTO(saved);
    }

    public void deleteById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found with id: " + id));
        personRepository.delete(person);
    }

    public Person getEntityById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "person not found with id: " + id));
    }
}
