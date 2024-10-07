package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EducationDTO {
    private  String educationLevel;
    private  String institution;
    private  String faculty;
    private  String specialization;
    private  int graduationYear;

}
