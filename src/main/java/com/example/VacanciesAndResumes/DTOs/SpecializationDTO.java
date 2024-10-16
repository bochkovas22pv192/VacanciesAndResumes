package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecializationDTO {
    @JsonProperty(value = "desired_position")
    private  String desiredPosition;
    private  String grade;
    @JsonProperty(value = "key_skills")
    private  String keySkills;
    private  int salary;
    private  String currency;
}
