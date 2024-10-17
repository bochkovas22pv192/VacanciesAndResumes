package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecializationDTO {
    @JsonProperty(value = "role_name")
    private  String roleName;
    private  String grade;
    private  int salary;
    private  String currency;
}
