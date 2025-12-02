package com.example.journalservice.ui;


import com.example.journalservice.core.model.Practitioner;
import com.example.journalservice.core.service.PractitionerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/practitioner")
public class PractitionerController {
    private final PractitionerService practitionerService;

    public PractitionerController(PractitionerService practitionerRepository) {
        this.practitionerService = practitionerRepository;
    }

    @PostMapping("/create")
    public Practitioner createPractitioner(@RequestBody Practitioner practitioner) {
        return practitionerService.save(practitioner);
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

