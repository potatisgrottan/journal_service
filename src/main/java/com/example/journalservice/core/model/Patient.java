package com.example.journalservice.core.model;

import com.example.journalservice.core.enums.HospitalRole;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    @Column(name="id", columnDefinition = "CHAR(36)")
    private UUID id;

    private String name;
    private String personalNumber;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HospitalRole role;

    public Patient() {}

    public Patient(String name) { this.name = name; }

    public Patient(String name, String personalNumber, Date dateOfBirth,
                   HospitalRole role, String address, String phoneNumber) {
        this.name = name;
        this.personalNumber = personalNumber;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }



    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPersonalNumber() { return personalNumber; }
    public void setPersonalNumber(String personalNumber) { this.personalNumber = personalNumber; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public HospitalRole getRole() { return role; }
    public void setRole(HospitalRole role) { this.role = role; }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
