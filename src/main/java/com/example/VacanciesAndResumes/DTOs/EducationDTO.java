package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EducationDTO {
    private  String educationLevel;
    private  String institution;
    private  String faculty;
    private  String specialization;
    private  Long graduationYear;

}
