package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecializationDTO {
    @JsonProperty(value = "role_name")
    private  String roleName;
    private  String grade;
    @JsonProperty(value = "key_skills")
    private String keySkills;
    private  int salary;
    private  String currency;
}
