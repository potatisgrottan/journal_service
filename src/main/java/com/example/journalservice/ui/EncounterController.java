package com.example.journalservice.ui;

import com.example.journalservice.core.model.Encounter;
import com.example.journalservice.core.service.EncounterService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/encounters")
public class EncounterController {

    private final EncounterService encounterService;

    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    @PostMapping
    public Encounter createEncounter(@RequestParam String userId,
                                     @RequestParam String patientId,
                                     @RequestParam String location) {

        return encounterService.addEncounter(
                userId,
                patientId,
                new Date(),
                location
        );
    }

    @GetMapping("/patient/{patientId}")
    public List<Encounter> getEncountersForPatient(@PathVariable String patientId) {
        return encounterService.findAllEncByPatient(patientId);
    }

    @GetMapping("/doctor/{practitionerId}/patient/{patientId}")
    public List<Encounter> doctorGetsEncounters(@PathVariable String practitionerId,
                                                @PathVariable String patientId) {
        return encounterService.findAllEncByPatient(patientId);
    }
}
