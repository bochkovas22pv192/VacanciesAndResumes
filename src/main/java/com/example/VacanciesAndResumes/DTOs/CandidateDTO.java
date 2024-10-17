package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateDTO {
    @JsonProperty(value = "last_name")
    private  String lastName;
    @JsonProperty(value = "first_name")
    private  String firstName;
    @JsonProperty(value = "middle_name")
    private  String middleName;
    private int gender;
    @JsonProperty(value = "birth_date")
    private  String birthDate;
    private  String country;
    private  String region;
    private  String city;
    private  String citizenship;
    @JsonProperty(value = "has_work_permit")
    private  boolean hasWorkPermit;
    private int relocate;
    private int travel;
}
