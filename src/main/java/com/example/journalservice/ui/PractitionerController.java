package com.example.journalservice.ui;


import com.example.journalservice.core.model.Practitioner;
import com.example.journalservice.core.service.PractitionerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/practitioner")
public class PractitionerController {
    private final PractitionerService practitionerService;

    public PractitionerController(PractitionerService practitionerRepository) {
        this.practitionerService = practitionerRepository;
    }

    @GetMapping("/all")
    public List<Practitioner> getAllPractitioners() {
        return  practitionerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Practitioner> findByPractitionerId(@PathVariable("id") String practitionerId) {
        return practitionerService.findById(practitionerId);
    }
}

