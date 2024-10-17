package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacancyDTO {
    private  String title;
    @JsonProperty(value = "role_name")
    private  String roleName;
    private  String description;
    private  int salary;
    private  String currency;
    private  String country;
    private  String region;
    private  String city;
}
