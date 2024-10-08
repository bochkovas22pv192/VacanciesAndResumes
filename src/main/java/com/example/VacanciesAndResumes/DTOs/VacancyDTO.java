package com.example.VacanciesAndResumes.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacancyDTO {
    private  String grade;
    private  String countryName;
    private  String regionName;
    private  String cityName;
    private  String vacanciesName;
    private  String role;
    private  String customerProject;
    private  String salary;
    private  String currency;
    private  String description;
}
