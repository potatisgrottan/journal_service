package com.example.journalservice.core.service;



import com.example.journalservice.core.model.Encounter;
import com.example.journalservice.core.model.Observation;
import com.example.journalservice.core.model.Patient;
import com.example.journalservice.db.EncounterRepository;
import com.example.journalservice.db.ObservationRepository;
import com.example.journalservice.db.PatientRepository;
import com.example.journalservice.ui.dto.PatientDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;
    private final EncounterRepository encounterRepository;
    private final ObservationRepository observationRepository;

    public PatientService(PatientRepository patientRepository, EncounterRepository encounterRepository, ObservationRepository observationRepository) {
        this.patientRepository = patientRepository;
        this.encounterRepository = encounterRepository;
        this.observationRepository = observationRepository;
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(String id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient findByName(String name){
        return patientRepository.findByName(name);
    }

    public Patient findByPersonalNumber(String personalNumber){
        return patientRepository.findByPersonalNumber(personalNumber);
    }

    public List<Patient> findByDateOfBirth(Date dateOfBirth){
        return patientRepository.findByDateOfBirth(dateOfBirth);
    }

    public Patient getPatientOverview(String patientId) {

        Patient patient = findById(patientId);
        if (patient == null) {
            throw new RuntimeException("Patient not found for id: " + patientId);
        }

        List<Encounter> encounters = encounterRepository.findByPatientId(patient.getId());

        List<Observation> observations = observationRepository.findByPatientId(patient.getId());


        return new PatientDTO(patient, encounters, observations).getPatient();
    }


    public Optional<Patient> findByUserId(String userId) {
        return patientRepository.findByUserId(userId);
    }
}
