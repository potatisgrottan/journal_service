package com.example.journalservice.core.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "encounter")
public class Encounter {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id",columnDefinition = "CHAR(36)")
    private String id;

    @Column(name = "dateofencounter")
    private Date dateOfEncounter;

    @Column(name = "patientid", columnDefinition = "CHAR(36)")
    private String patientId;

    @Column(name = "practitionerid", columnDefinition = "CHAR(36)")
    private String practitionerId;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Observation> observations = new ArrayList<>();


    public Encounter() {}

    public Encounter(String patientId, String practitionerId, Date dateOfEncounter, String location) {
        this.patientId = patientId;
        this.practitionerId = practitionerId;
        this.dateOfEncounter = dateOfEncounter;
        this.location = location;
    }

    public String getId() { return id; }
    public void setId(String encounterId) { this.id = encounterId; }

    public Date getDateOfEncounter() { return dateOfEncounter; }
    public void setDateOfEncounter(Date dateOfEncounter) { this.dateOfEncounter = dateOfEncounter; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getPractitionerId() { return practitionerId; }
    public void setPractitionerId(String practitionerId) { this.practitionerId = practitionerId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<Observation> getObservations() { return observations; }
    public void setObservations(List<Observation> observations) { this.observations = observations; }

    public void addObservation(Observation observation) {
        observations.add(observation);
        observation.setEncounter(this);
    }

    public void removeObservation(Observation observation) {
        observations.remove(observation);
        observation.setEncounter(null);
    }

    @Override
    public String toString() {
        return "Encounter{" +
                "encounterId=" + id +
                ", patientId=" + patientId +
                ", practitionerId=" + practitionerId +
                ", dateOfEncounter=" + dateOfEncounter +
                ", location='" + location + '\'' +
                ", observations=" + observations.size() +
                '}';
    }
}

