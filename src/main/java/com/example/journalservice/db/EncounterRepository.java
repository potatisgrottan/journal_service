package com.example.journalservice.db;

import com.example.journalservice.core.model.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EncounterRepository extends JpaRepository<Encounter, UUID> {
    List<Encounter> findAllByPatientId(UUID patientId);
    List<Encounter> findAllByPractitionerId(UUID practitionerId);
    List<Encounter> findByPatientId(UUID patientId);
}

