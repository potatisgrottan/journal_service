package com.example.journalservice.core.service;



import com.example.journalservice.core.model.Encounter;
import com.example.journalservice.core.model.Observation;
import com.example.journalservice.core.model.Patient;
import com.example.journalservice.db.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(UUID id) {
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

    public Patient getPatientOverview(UUID patientId) {

        Patient patient = findById(patientId);
        if (patient == null) {
            throw new RuntimeException("Patient not found for id: " + patientId);
        }

        List<Encounter> encounters = encounterRepository.findByPatientId(patient.getId());

        List<Observation> observations = observationRepository.findByPatientId(patient.getId());

        List<ObservationDTO> observationDtos = observations.stream()
                .map(ObservationDTO::new)
                .toList();

        return new PatientOverviewDTO(patient, encounters, observationDtos);
    }
    public Optional<Patient> findByUserId(UUID userId) {
        return patientRepository.findByUserId(userId);
    }
}
