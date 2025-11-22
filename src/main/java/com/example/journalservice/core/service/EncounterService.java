package com.example.journalservice.core.service;


import com.example.journalservice.core.model.Encounter;
import com.example.journalservice.core.model.Observation;
import com.example.journalservice.core.model.Patient;
import com.example.journalservice.core.model.Practitioner;
import com.example.journalservice.db.EncounterRepository;
import com.example.journalservice.db.ObservationRepository;
import com.example.journalservice.ui.dto.ObservationDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class EncounterService {

    private final EncounterRepository encounterRepository;
    private final PatientService patientService;
    private final PractitionerService practitionerService;
    private final ObservationRepository observationRepository;

    public EncounterService(EncounterRepository encounterRepository,
                            PatientService patientService,
                            PractitionerService practitionerService, ObservationRepository observationRepository) {
        this.encounterRepository = encounterRepository;
        this.patientService = patientService;
        this.practitionerService = practitionerService;
        this.observationRepository = observationRepository;
    }

/*TODO FIX SO IT DONT USE USER AND WORKS*/
    /*public List<Encounter> getEncountersForUser(User userId) {

        switch (user.getRole()) {

            case "DOCTOR":
            case "STAFF": {
                Practitioner practitioner = practitionerService.findById(user.getId())
                        .orElseThrow(() -> new RuntimeException("Practitioner not found for user id: " + user.getId()));
                if (practitioner == null) {
                    throw new RuntimeException("Practitioner not found for user id: " + user.getId());
                }
                return findAllByPractitioner(practitioner.getId());
            }

            case "PATIENT": {
                Patient patient = patientService.findById(user.getId());
                if (patient == null) {
                    throw new RuntimeException("Patient not found for user id: " + user.getId());
                }
                return findAllByPatient(patient.getId());
            }

            default:
                throw new IllegalArgumentException("Unknown user role: " + user.getRole());
        }
    }*/


    public Encounter addEncounter(UUID userId, UUID patientId, Date date, String location) {

        Practitioner practitioner = practitionerService.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Practitioner not found for user id: " + userId));

        Patient patient = patientService.findById(patientId);
        if (patient == null) {
            throw new RuntimeException("Patient not found for id: " + patientId);
        }

        Encounter encounter = new Encounter();
        encounter.setPractitionerId(practitioner.getId());
        encounter.setPatientId(patient.getId());
        encounter.setDateOfEncounter(date);
        encounter.setLocation(location);

        return encounterRepository.save(encounter);
    }



    public List<Encounter> findAllEncByPatient(UUID patientId) {
        return encounterRepository.findAllByPatientId(patientId);
    }

    public List<Encounter> findAllByPractitioner(UUID practitionerId) {
        return encounterRepository.findAllByPractitionerId(practitionerId);
    }

    public Encounter save(Encounter encounter) {
        return encounterRepository.save(encounter);
    }

    public Optional<Encounter> findById(UUID encounterId) {
        return encounterRepository.findById(encounterId);
    }

    public Observation save(Observation observation) {
        return observationRepository.save(observation);
    }

    public List<ObservationDTO> findAllObsByPatient(UUID patientId) {
        return observationRepository.findAllByPatientId(patientId)
                .stream()
                .map(ObservationDTO::new)
                .toList();
    }

    public List<ObservationDTO> findAllByEncounter(UUID encounterId) {
        return observationRepository.findAllByEncounterId(encounterId).stream().map(ObservationDTO::new).toList();
    }

    public Observation addObservation(UUID encounterId, Observation dto) {

        Encounter encounter = encounterRepository.findById(encounterId)
                .orElseThrow(() -> new RuntimeException("Encounter not found"));

        Observation obs = new Observation();
        obs.setEncounter(encounter);
        obs.setPatientId(encounter.getPatientId());
        obs.setPractitionerId(encounter.getPractitionerId());
        obs.setObservation(dto.getObservation());
        obs.setTimeOfObservation(dto.getTimeOfObservation() != null ? dto.getTimeOfObservation() : new Date());

        return  observationRepository.save(obs);
    }

}

