package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdditionalInfoDTO {
    @JsonProperty(value = "willing_to_relocate")
    private  boolean willingToRelocate;
    @JsonProperty(value = "employment_type")
    private  String employmentType;
    @JsonProperty(value = "willing_to_travel")
    private  boolean willingToTravel;
}
