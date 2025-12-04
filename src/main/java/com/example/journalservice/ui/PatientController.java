package com.example.journalservice.ui;

import com.example.journalservice.core.service.AuthClient;
import com.example.journalservice.core.service.PatientService;
import com.example.journalservice.ui.dto.PatientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {


    private final PatientService patientService;
    private final AuthClient authClient;

    public PatientController(PatientService patientService, AuthClient authClient) {
        this.patientService = patientService;
        this.authClient = authClient;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPatients(@RequestHeader("Authorization") String authHeader) {

        List<PatientResponse> patients = patientService.getAllPatients(authHeader);

        return ResponseEntity.ok(patients);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getPatientByEmail(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String email) {

        PatientResponse p = patientService.getPatientByEmail(email, authHeader);

        if (p == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(p);
    }
}
