package com.example.journalservice.core.model;

import com.example.journalservice.core.enums.HospitalRole;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Practitioner {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private UUID id;

    private String name;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private HospitalRole hospitalRole;



    public Practitioner() {}

    public Practitioner(String name, String phoneNumber, HospitalRole hospitalRole) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.hospitalRole = hospitalRole;
    }


    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public HospitalRole getRole() { return hospitalRole; }
    public void setRole(HospitalRole hospitalRole) { this.hospitalRole = hospitalRole; }

    @Override
    public String toString() {
        return "Practitioner{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", role=" + hospitalRole +
                '}';
    }
}
