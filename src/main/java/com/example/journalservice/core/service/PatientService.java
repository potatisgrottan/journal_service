package com.example.journalservice.core.service;

import com.example.journalservice.core.service.AuthClient;
import com.example.journalservice.ui.dto.PatientResponse;
import com.example.journalservice.ui.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final AuthClient authClient;

    public PatientService(AuthClient authClient) {
        this.authClient = authClient;
    }

    public List<PatientResponse> getAllPatients(String authHeader) {

        List<UserDto> allUsers = authClient.getAllPatients(authHeader);

        return allUsers.stream()
                .filter(u -> "PATIENT".equals(u.role()))
                .map(u -> new PatientResponse(
                        u.email(),
                        u.fullName(),
                        null,
                        null,
                        null,
                        null
                ))
                .toList();
    }

    public PatientResponse getPatientByEmail(String email, String authHeader) {

        List<PatientResponse> allUsers = authClient.getAllPatients(authHeader);

        return allUsers.stream()
                .filter(u -> u.email().equals(email))
                .findFirst()
                .map(u -> new PatientResponse(
                        u.email(),
                        u.name(),
                        u.personalNumber(),
                        u.address(),
                        u.phoneNumber(),
                        null
                ))
                .orElse(null);
    }
}
