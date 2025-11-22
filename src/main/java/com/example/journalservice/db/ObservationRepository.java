package com.example.journalservice.db;

import com.example.journalservice.core.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ObservationRepository extends JpaRepository<Observation, UUID> {
    //List<Observation> findByPatient(Patient patientId);
    //List<Observation> findByTimeOfObservation(Date date);
    //List<Observation> findByEncounter(Encounter encounter);
    List<Observation> findAllByPatientId(UUID patientId) ;
    List<Observation> findAllByEncounterId(UUID encounterId);
    List<Observation> findByPatientId(UUID patientId);
}
