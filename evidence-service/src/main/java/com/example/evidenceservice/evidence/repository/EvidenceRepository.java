package com.example.evidenceservice.evidence.repository;

import com.example.evidenceservice.evidence.model.Evidence;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvidenceRepository extends JpaRepository<Evidence, Long> {

    List<Evidence> findByCaseId(Long caseId);

    List<Evidence> findByInvestigatorCollectId(Long investigatorCollectId);

    List<Evidence> findByInvestigatorApprovedId(Long investigatorApprovedId);
}
