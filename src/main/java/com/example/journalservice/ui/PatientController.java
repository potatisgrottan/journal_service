package com.example.journalservice.ui;



import com.example.journalservice.core.model.Patient;
import com.example.journalservice.core.service.PatientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;
   // private final PatientOverviewService overviewService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
        //this.overviewService = overviewService;
    }

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/id/{id}")
    public Patient getPatient(@PathVariable String id) {
        return patientService.findById(id);
    }

    @GetMapping("/name/{name}")
    public Patient getPatientByName(@PathVariable String name) {
        return patientService.findByName(name);
    }

    @GetMapping("/personalNumber/{personalNumber}")
    public Patient getPatientsByPersonalNumber(@PathVariable String personalNumber) {
        return patientService.findByPersonalNumber(personalNumber);
    }
    @GetMapping("/dateOfBirth/{dateOfBirth}")
    public List<Patient> getPatientsByDateOfBirth(@PathVariable Date dateOfBirth) {
        return patientService.findByDateOfBirth(dateOfBirth);
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @GetMapping("/{patientId}/overview")
    public Patient getOverview(@PathVariable String patientId) {
        return patientService.getPatientOverview(patientId);
    }

    @GetMapping("/me")
    public Patient getMyOverview(@AuthenticationPrincipal Patient currentUser) {
        if (!"PATIENT".equals(currentUser.getRole())) {
            throw new RuntimeException("Only patients can view their own overview");
        }

        Patient patient = patientService.findByUserId(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("Patient not found for user id: " + currentUser.getId()));

        return patientService.getPatientOverview(patient.getId());
    }

}

