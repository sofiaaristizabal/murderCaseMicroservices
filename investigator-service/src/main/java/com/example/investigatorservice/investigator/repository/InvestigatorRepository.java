package com.example.investigatorservice.investigator.repository;

import com.example.investigatorservice.investigator.model.Investigator;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestigatorRepository extends JpaRepository<Investigator, Long> {

    List<Investigator> findByCaseId(Long caseId);
}
