package com.example.journalservice.core.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "encounter")
public class Encounter {

    @Id
    @GeneratedValue
    @Column(name = "encounterid", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "dateofencounter")
    private Date dateOfEncounter;

    @Column(name = "patientid", columnDefinition = "uuid")
    private UUID patientId;

    @Column(name = "practitionerid", columnDefinition = "uuid")
    private UUID practitionerId;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "encounter", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Observation> observations = new ArrayList<>();


    public Encounter() {}

    public Encounter(UUID patientId, UUID practitionerId, Date dateOfEncounter, String location) {
        this.patientId = patientId;
        this.practitionerId = practitionerId;
        this.dateOfEncounter = dateOfEncounter;
        this.location = location;
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID encounterId) { this.id = encounterId; }

    public Date getDateOfEncounter() { return dateOfEncounter; }
    public void setDateOfEncounter(Date dateOfEncounter) { this.dateOfEncounter = dateOfEncounter; }

    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }

    public UUID getPractitionerId() { return practitionerId; }
    public void setPractitionerId(UUID practitionerId) { this.practitionerId = practitionerId; }

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

