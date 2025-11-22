package com.example.journalservice.db;



import com.example.journalservice.core.enums.HospitalRole;
import com.example.journalservice.core.model.Practitioner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PractitionerRepository extends JpaRepository<Practitioner, UUID> {
    Optional<Practitioner> findById(UUID Id);
    Practitioner findByName(String practitionerName);
    List<Practitioner> findByHospitalRole(HospitalRole role);

    Optional<Practitioner> findByUserId(UUID userId);
}
