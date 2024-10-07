package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecializationDTO {
    private  String desiredPosition;
    private  String grade;
    private  String keySkills;
    private  double salary;
    private  String currency;
}
