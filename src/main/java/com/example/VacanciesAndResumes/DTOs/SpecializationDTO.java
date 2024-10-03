package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SpecializationDTO {
    private  String desiredPosition;
    private  String grade;
    private  String keySkills;
    private  Long salary;
    private  String currency;
}
