package com.example.personservice.person.controller;

import com.example.personservice.person.dto.PersonCreateDTO;
import com.example.personservice.person.dto.PersonRespuestaDTO;
import com.example.personservice.person.dto.PersonUpdateDTO;
import com.example.personservice.person.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonRespuestaDTO> create(@Valid @RequestBody PersonCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PersonRespuestaDTO>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonRespuestaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<PersonRespuestaDTO>> findByCaseId(@PathVariable Long caseId) {
        return ResponseEntity.ok(personService.findByCaseId(caseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonRespuestaDTO> update(@PathVariable Long id, @Valid @RequestBody PersonUpdateDTO dto) {
        return ResponseEntity.ok(personService.updateById(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
