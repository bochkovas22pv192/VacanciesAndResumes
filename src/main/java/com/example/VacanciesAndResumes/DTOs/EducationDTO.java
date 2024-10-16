package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EducationDTO {
    @JsonProperty(value = "education_level")
    private  String educationLevel;
    private  String institution;
    private  String faculty;
    private  String specialization;
    @JsonProperty(value = "graduation_year")
    private  int graduationYear;

}
