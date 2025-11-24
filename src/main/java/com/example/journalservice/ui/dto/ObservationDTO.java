package com.example.journalservice.ui.dto;

import com.example.journalservice.core.model.Observation;

import java.util.Date;


public class ObservationDTO {

    private String id;
    private String encounterId;
    private String observationText;
    private Date timeOfObservation;

    public ObservationDTO() {}

    public ObservationDTO(Observation o) {
        this.id = o.getId();
        this.observationText = o.getObservation();
        this.timeOfObservation = o.getTimeOfObservation();
        this.encounterId = o.getEncounter().getId();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEncounterId() { return encounterId; }
    public void setEncounterId(String encounterId) { this.encounterId = encounterId; }

    public String getObservationText() { return observationText; }
    public void setObservationText(String observationText) { this.observationText = observationText; }

    public Date getTimeOfObservation() { return timeOfObservation; }
    public void setTimeOfObservation(Date timeOfObservation) { this.timeOfObservation = timeOfObservation; }
}
