package com.example.journalservice.db;



import com.example.journalservice.core.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface PatientRepository extends JpaRepository<Patient, String> {


    Patient findByName(String name);

    Patient findByPersonalNumber(String personalNumber);

    List<Patient> findByDateOfBirth(Date dateOfBirth);

    Optional<Patient> findByUserId(String userId);

}
