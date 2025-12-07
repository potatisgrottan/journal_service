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


    @Column(name = "patientEmail", columnDefinition = "VARCHAR(225)")
    private String patientEmail;

    @Column(name = "practitionerEmail", columnDefinition = "VARCHAR(225)")
    private String practitionerEmail;

    @Column(name = "observationtext")
    private String observation;

    @Column(name = "timeofobservation")
    private Date timeOfObservation;

    @Column(name = "image_id", columnDefinition = "VARCHAR(225)")
    private String imageId;

    public Observation() {
        this.timeOfObservation = new Date();
    }

    public Observation(Encounter encounter, String patientEmail, String practitionerEmail, String observation, Date timeOfObservation, String imageId) {
        this.encounter = encounter;
        this.patientEmail = patientEmail;
        this.practitionerEmail = practitionerEmail;
        this.observation = observation;
        this.timeOfObservation = new Date();
        this.imageId = imageId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Encounter getEncounter() { return encounter; }
    public void setEncounter(Encounter encounter) { this.encounter = encounter; }

    public String getpatientEmail() { return patientEmail; }
    public void setpatientEmail(String patientEmail) { this.patientEmail = patientEmail; }

    public String getPractitionerEmail() { return practitionerEmail; }
    public void setPractitionerEmail(String practitionerEmail) { this.practitionerEmail = practitionerEmail; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public Date getTimeOfObservation() { return timeOfObservation; }
    public void setTimeOfObservation(Date timeOfObservation) { this.timeOfObservation = timeOfObservation; }

    public String getImageId() { return imageId; }
    public void setImageId(String imageId) { this.imageId = imageId; }

    @Override
    public String toString() {
        return "Observation{" +
                "id=" + id +
                ", encounter=" + (encounter != null ? encounter.getId() : null) +
                ", patientEmail=" + patientEmail +
                ", practitionerEmail=" + practitionerEmail +
                ", observation='" + observation + '\'' +
                ", timeOfObservation=" + timeOfObservation +
                '}';
    }
}


