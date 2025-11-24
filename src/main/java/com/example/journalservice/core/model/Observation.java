package com.example.journalservice.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Entity
@Table(name="observation")
public class Observation {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encounterid", nullable = false)
    @JsonBackReference
    private Encounter encounter;


    @Column(name = "patientid", columnDefinition = "CHAR(36)")
    private String patientId;

    @Column(name = "practitionerid", columnDefinition = "CHAR(36)")
    private String practitionerId;

    @Column(name = "observationtext")
    private String observation;

    @Column(name = "timeofobservation")
    private Date timeOfObservation;

    public Observation() {
        this.timeOfObservation = new Date();
    }

    public Observation(Encounter encounter, String patientId, String practitionerId, String observation) {
        this.encounter = encounter;
        this.patientId = patientId;
        this.practitionerId = practitionerId;
        this.observation = observation;
        this.timeOfObservation = new Date();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Encounter getEncounter() { return encounter; }
    public void setEncounter(Encounter encounter) { this.encounter = encounter; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getPractitionerId() { return practitionerId; }
    public void setPractitionerId(String practitionerId) { this.practitionerId = practitionerId; }

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


