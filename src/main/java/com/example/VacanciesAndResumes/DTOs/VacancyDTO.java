package com.example.VacanciesAndResumes.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacancyDTO {
    private  String title;
    private  String roleName;
    private  String description;
    private  int salary;
    private  String country;
    private  String region;
    private  String city;
    private String createdAt;
    private boolean isActive;
}
