package com.example.journalservice.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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


