package com.example.journalservice.ui.dto;

import java.util.Date;
import java.util.List;

public class EncounterWithObservationsDTO {

    public String id;
    public Date dateOfEncounter;
    public String patientEmail;
    public String practitionerEmail;
    public String location;
    public List<ObservationDTO> observations;

    public EncounterWithObservationsDTO() {}

    public EncounterWithObservationsDTO(
            String id,
            Date dateOfEncounter,
            String patientEmail,
            String practitionerEmail,
            String location,
            List<ObservationDTO> observations
    ) {
        this.id = id;
        this.dateOfEncounter = dateOfEncounter;
        this.patientEmail = patientEmail;
        this.practitionerEmail = practitionerEmail;
        this.location = location;
        this.observations = observations;
    }
}
