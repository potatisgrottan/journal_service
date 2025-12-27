package com.example.journalservice.core.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

