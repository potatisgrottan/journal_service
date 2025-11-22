package com.example.journalservice.ui.dto;

import com.example.journalservice.core.model.Observation;

import java.util.Date;
import java.util.UUID;

public class ObservationDTO {

    private UUID id;
    private UUID encounterId;
    private String observationText;
    private Date timeOfObservation;

    public ObservationDTO() {}

    public ObservationDTO(Observation o) {
        this.id = o.getId();
        this.observationText = o.getObservation();
        this.timeOfObservation = o.getTimeOfObservation();
        this.encounterId = o.getEncounter().getId();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEncounterId() { return encounterId; }
    public void setEncounterId(UUID encounterId) { this.encounterId = encounterId; }

    public String getObservationText() { return observationText; }
    public void setObservationText(String observationText) { this.observationText = observationText; }

    public Date getTimeOfObservation() { return timeOfObservation; }
    public void setTimeOfObservation(Date timeOfObservation) { this.timeOfObservation = timeOfObservation; }
}
