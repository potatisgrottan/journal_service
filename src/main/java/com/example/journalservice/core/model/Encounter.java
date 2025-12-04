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

    @Column(name = "patientEmail", columnDefinition = "VARCHAR(225)")
    private String patientEmail;

    @Column(name = "practitionerEmail", columnDefinition = "VARCHAR(225)")
    private String practitionerEmail;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Observation> observations = new ArrayList<>();


    public Encounter() {}

    public Encounter(String patientEmail, String practitionerEmail, Date dateOfEncounter, String location) {
        this.patientEmail = patientEmail;
        this.practitionerEmail = Encounter.this.practitionerEmail;
        this.dateOfEncounter = dateOfEncounter;
        this.location = location;
    }

    public String getId() { return id; }
    public void setId(String encounterId) { this.id = encounterId; }

    public Date getDateOfEncounter() { return dateOfEncounter; }
    public void setDateOfEncounter(Date dateOfEncounter) { this.dateOfEncounter = dateOfEncounter; }

    public String getPatientEmail() { return patientEmail; }
    public void setPatientEmail(String patientEmail) { this.patientEmail = patientEmail; }

    public String getpractitionerEmail() { return practitionerEmail; }
    public void setpractitionerEmail(String practitionerEmail) { this.practitionerEmail = Encounter.this.practitionerEmail; }

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
                ", patientEmail=" + patientEmail +
                ", practitionerEmail=" + practitionerEmail +
                ", dateOfEncounter=" + dateOfEncounter +
                ", location='" + location + '\'' +
                ", observations=" + observations.size() +
                '}';
    }
}

