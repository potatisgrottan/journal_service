package com.example.journalservice.ui.dto;

import com.example.journalservice.core.model.Encounter;
import com.example.journalservice.core.model.Observation;
import com.example.journalservice.core.model.Patient;

import java.util.List;

public class PatientDTO {

    private Patient patient;
    private List<Encounter> encounters;
    private List<Observation> observations;

    public PatientDTO(
            Patient patient,
            List<Encounter> encounters,
            List<Observation> observations
    ) {
        this.patient = patient;
        this.encounters = encounters;
        this.observations = observations;
    }

    public Patient getPatient() { return patient; }
    public List<Encounter> getEncounters() { return encounters; }
    public List<Observation> getObservations() { return observations; }
}
