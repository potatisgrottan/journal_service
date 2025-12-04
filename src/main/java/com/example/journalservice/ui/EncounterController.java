package com.example.journalservice.ui;

import com.example.journalservice.core.model.Encounter;
import com.example.journalservice.core.service.EncounterService;
import com.example.journalservice.ui.dto.ObservationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/encounters")
public class EncounterController {

    private final EncounterService encounterService;

    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    @PostMapping("/make")
    public Encounter createEncounter(@RequestBody Encounter encounter) {
        return encounterService.addEncounter(
                encounter.getpractitionerEmail(),
                encounter.getPatientEmail(),
                new Date(),
                encounter.getLocation()
        );
    }


    @GetMapping("/patient/{patientId}")
    public List<Encounter> getEncountersForPatient(@PathVariable String patientId) {
        return encounterService.findAllEncByPatient(patientId);
    }

    @GetMapping("/doctor/{practitionerEmail}/patient/{patientEmail}")
    public List<Encounter> doctorGetsEncounters(@PathVariable String practitionerEmail,
                                                @PathVariable String patientEmail) {
        return encounterService.findAllEncByPatient(patientEmail);
    }

    @GetMapping("/{encounterId}/observations")
    public List<ObservationDTO> getObservationsByEncounter(@PathVariable String encounterId) {
        return encounterService.findAllByEncounter(encounterId);
    }


    @GetMapping("/patient/{patientEmail}/observations")
    public List<ObservationDTO> getObservationsByPatient(@PathVariable String patientEmail) {
        return encounterService.findAllObsByPatient(patientEmail);
    }

    @PostMapping("/{encounterId}/observations")
    public ResponseEntity<String> addObservation(
            @PathVariable String encounterId,
            @RequestBody ObservationDTO dto) {

        Optional<Encounter> encounter = encounterService.findById(encounterId);
        // Kontroll: encounter måste existera
        if (encounter == null) {
            return ResponseEntity.badRequest().body("Encounter not found");
        }

        encounterService.addObservation(encounterId,dto);
        return ResponseEntity.ok("Observation saved successfully!");
    }
}
