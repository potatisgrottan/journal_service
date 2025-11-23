package com.example.journalservice.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="observation")
public class Observation {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounterid", nullable = false)
    @JsonBackReference
    private Encounter encounter;


    @Column(name = "patientid", columnDefinition = "CHAR(36)")
    private UUID patientId;

    @Column(name = "practitionerid", columnDefinition = "CHAR(36)")
    private UUID practitionerId;

    @Column(name = "observationtext")
    private String observation;

    @Column(name = "timeofobservation")
    private Date timeOfObservation;

    public Observation() {
        this.timeOfObservation = new Date();
    }

    public Observation(Encounter encounter, UUID patientId, UUID practitionerId, String observation) {
        this.encounter = encounter;
        this.patientId = patientId;
        this.practitionerId = practitionerId;
        this.observation = observation;
        this.timeOfObservation = new Date();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Encounter getEncounter() { return encounter; }
    public void setEncounter(Encounter encounter) { this.encounter = encounter; }

    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }

    public UUID getPractitionerId() { return practitionerId; }
    public void setPractitionerId(UUID practitionerId) { this.practitionerId = practitionerId; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public Date getTimeOfObservation() { return timeOfObservation; }
    public void setTimeOfObservation(Date timeOfObservation) { this.timeOfObservation = timeOfObservation; }

    @Override
    public String toString() {
        return "Observation{" +
                "id=" + id +
                ", encounter=" + (encounter != null ? encounter.getId() : null) +
                ", patientId=" + patientId +
                ", practitionerId=" + practitionerId +
                ", observation='" + observation + '\'' +
                ", timeOfObservation=" + timeOfObservation +
                '}';
    }
}


