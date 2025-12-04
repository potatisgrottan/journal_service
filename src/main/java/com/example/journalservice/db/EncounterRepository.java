package com.example.journalservice.db;

import com.example.journalservice.core.model.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EncounterRepository extends JpaRepository<Encounter, String> {
    /*List<Encounter> findAllByPatientId(String patientId);
    List<Encounter> findAllByPractitionerId(String practitionerId);
    List<Encounter> findByPatientId(String patientId);*/
    List<Encounter> findAllByPatientEmail(String patientEmail);
    List<Encounter> findAllByPractitionerEmail(String practitionerEmail);

}

