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
    @Value("http://auth-service:8081")
    private String authServiceUrl;

    @Autowired
    public AuthClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateBasicAuth(String basicAuthHeader) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", basicAuthHeader);

            HttpEntity<Void> request = new HttpEntity<>(headers);

            String url = authServiceUrl+"/api/auth/validate";

            ResponseEntity<UserDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    UserDto.class
            );

            return response.getBody();
        } catch (Exception e) {
            System.out.println("Auth validation failed: " + e.getMessage());
            return null;
        }
    }

    public List<PatientResponse> getAllPatients(String authHeader) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);

            HttpEntity<Void> request = new HttpEntity<>(headers);

            String url = authServiceUrl + "/api/auth/users/role/PATIENT";

            ResponseEntity<PatientResponse[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    PatientResponse[].class
            );

            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            System.out.println("Failed to fetch users from auth-service: " + e.getMessage());
            return List.of();
        }
    }
}

