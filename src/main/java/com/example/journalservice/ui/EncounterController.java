package com.example.journalservice.ui;

import com.example.journalservice.core.model.Encounter;
import com.example.journalservice.core.service.EncounterService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/encounters")
public class EncounterController {

    private final EncounterService encounterService;

    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    @PostMapping
    public Encounter createEncounter(@RequestParam UUID userId,
                                     @RequestParam UUID patientId,
                                     @RequestParam String location) {

        return encounterService.addEncounter(
                userId,
                patientId,
                new Date(),
                location
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<Encounter> getEncountersForPatient(@PathVariable UUID patientId) {
        return encounterService.findAllEncByPatient(patientId);
    }

    @GetMapping("/doctor/{practitionerId}/patient/{patientId}")
    public List<Encounter> doctorGetsEncounters(@PathVariable UUID practitionerId,
                                                @PathVariable UUID patientId) {
        return encounterService.findAllEncByPatient(patientId);
    }
}
