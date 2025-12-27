package com.example.journalservice.core.service;

import com.example.journalservice.ui.dto.PatientResponse;
import com.example.journalservice.ui.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthClient {

    private final RestTemplate restTemplate;
    @Value("http://auth-servicea:8081")

    private String authServiceUrl;

    @Autowired
    public AuthClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // OBS: validateBasicAuth är BORTTAGEN. Vi litar på JWT-token nu.

    // Denna metod är kvar för att hämta patientlistan
    public List<PatientResponse> getAllPatients(String authHeader) {
        try {
            HttpHeaders headers = new HttpHeaders();
            // AuthHeader kommer nu vara "Bearer <token>" istället för "Basic ..."
            // Eftersom Auth Service också kör OAuth2 nu, fungerar detta direkt!
            headers.set("Authorization", authHeader);

            HttpEntity<Void> request = new HttpEntity<>(headers);

            String url = authServiceUrl + "/api/auth/users/role/PATIENT";

            ResponseEntity<PatientResponse[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    PatientResponse[].class
            );

            return response.getBody() != null ? Arrays.asList(response.getBody()) : List.of();
        } catch (Exception e) {
            System.out.println("Failed to fetch users from auth-service: " + e.getMessage());
            return List.of();
        }
    }
}