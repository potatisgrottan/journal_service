package com.example.journalservice.core.model;

import com.example.journalservice.core.enums.HospitalRole;
import jakarta.persistence.*;


@Entity
public class Practitioner {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

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


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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
