package com.example.journalservice.db;

import com.example.journalservice.core.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ObservationRepository extends JpaRepository<Observation, String> {
    //List<Observation> findByPatient(Patient patientId);
    //List<Observation> findByTimeOfObservation(Date date);
    //List<Observation> findByEncounter(Encounter encounter);
    List<Observation> findAllByPatientId(String patientId) ;
    List<Observation> findAllByEncounterId(String encounterId);
    List<Observation> findByPatientId(String patientId);
}
