package com.example.journalservice.core.service;




import com.example.journalservice.core.model.Practitioner;
import com.example.journalservice.db.PractitionerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PractitionerService {
    private final PractitionerRepository practitionerRepository;

    public PractitionerService(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    public Practitioner findByName(String practitionerName) {
        return practitionerRepository.findByName(practitionerName);
    }


    public Optional<Practitioner> findById(UUID id) {
        return practitionerRepository.findById(id);
    }

    public Optional<Practitioner> findByUserId(UUID userId) {
        return practitionerRepository.findByUserId(userId);
    }


    public List<Practitioner> findAll() {
        return practitionerRepository.findAll();
    }

    public Practitioner save(Practitioner practitioner) {
        return practitionerRepository.save(practitioner);
    }
}

