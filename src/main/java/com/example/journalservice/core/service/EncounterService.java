package com.example.journalservice.core.service;


import com.example.journalservice.core.model.Encounter;
import com.example.journalservice.core.model.Observation;
//import com.example.journalservice.core.model.Patient;
//import com.example.journalservice.core.model.Practitioner;
import com.example.journalservice.db.EncounterRepository;
import com.example.journalservice.db.ObservationRepository;
import com.example.journalservice.ui.dto.EncounterWithObservationsDTO;
import com.example.journalservice.ui.dto.ObservationDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EncounterService {

    private final EncounterRepository encounterRepository;
   // private final PatientService patientService;
   // private final PractitionerService practitionerService;
    private final ObservationRepository observationRepository;

    public EncounterService(EncounterRepository encounterRepository,
                            ObservationRepository observationRepository) {
        this.encounterRepository = encounterRepository;
        /*this.patientService = patientService;
        this.practitionerService = practitionerService;*/
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


    public Encounter addEncounter(String practitionerEmail, String patientEmail, Date date, String location) {
        Encounter encounter = new Encounter();
        encounter.setpractitionerEmail(practitionerEmail);
        encounter.setPatientEmail(patientEmail);
        encounter.setDateOfEncounter(date);
        encounter.setLocation(location);
        return encounterRepository.save(encounter);
    }




    public List<Encounter> findAllEncByPatient(String patientEmail) {
        return encounterRepository.findAllByPatientEmail(patientEmail);
    }

    public List<Encounter> findAllByPractitioner(String practitionerEmail) {
        return encounterRepository.findAllByPractitionerEmail(practitionerEmail);
    }


    public Encounter save(Encounter encounter) {
        return encounterRepository.save(encounter);
    }

    public Optional<Encounter> findById(String encounterId) {
        return encounterRepository.findById(encounterId);
    }

    public Observation save(Observation observation) {
        return observationRepository.save(observation);
    }

    public List<ObservationDTO> findAllObsByPatient(String patientEmail) {
        return observationRepository.findAllByPatientEmail(patientEmail)
                .stream()
                .map(ObservationDTO::new)
                .toList();
    }

    public List<ObservationDTO> findAllByEncounter(String encounterId) {
        return observationRepository.findAllByEncounterId(encounterId).stream().map(ObservationDTO::new).toList();
    }

    public Observation addObservation(String encounterId, ObservationDTO dto) {
        Encounter encounter = encounterRepository.findById(encounterId)
                .orElseThrow(() -> new RuntimeException("Encounter not found"));

        Observation obs = new Observation();
        obs.setEncounter(encounter);
        obs.setpatientEmail(encounter.getPatientEmail());
        obs.setPractitionerEmail(encounter.getpractitionerEmail());
        obs.setObservation(dto.getObservationText());
        obs.setTimeOfObservation(dto.getTimeOfObservation() != null ? dto.getTimeOfObservation() : new Date());

        return observationRepository.save(obs);
    }


    public List<EncounterWithObservationsDTO> getFullOverviewForPatient(String email) {

        var encounters = encounterRepository.findAllByPatientEmail(email);

        return encounters.stream()
                .map(e -> new EncounterWithObservationsDTO(
                        e.getId(),
                        e.getDateOfEncounter(),
                        e.getPatientEmail(),
                        e.getpractitionerEmail(),
                        e.getLocation(),
                        observationRepository.findAllByEncounterId(e.getId())
                                .stream()
                                .map(ObservationDTO::new)
                                .toList()
                ))
                .toList();
    }



}

