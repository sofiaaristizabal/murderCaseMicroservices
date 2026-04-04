package com.example.personservice.person.repository;

import com.example.personservice.person.model.Person;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByCaseId(Long caseId);
}
