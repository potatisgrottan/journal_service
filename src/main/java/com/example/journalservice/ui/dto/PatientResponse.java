package com.example.journalservice.ui.dto;

import java.time.LocalDate;

public record PatientResponse(
        String email,
        String name,
        String personalNumber,
        String address,
        String phoneNumber,
        LocalDate dateOfBirth
) {}
