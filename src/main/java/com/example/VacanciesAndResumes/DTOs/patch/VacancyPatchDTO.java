package com.example.VacanciesAndResumes.DTOs.patch;

import com.example.VacanciesAndResumes.DTOs.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacancyPatchDTO {
    private  String title;
    @JsonProperty(value = "role_name")
    private  String roleName;
    private  String description;
    private  int salary;
    private  String currency;
    private  String grade;
    private  String country;
    private  String region;
    private  String city;
    @JsonProperty(value = "is_active")
    private boolean isActive;
}
