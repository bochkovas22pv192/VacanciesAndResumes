package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkExperienceDTO {
    private  String organizationName;
    private  String industry;
    private  String organizationWebsite;
    private  String companyCity;
    private  String position;
    private  String startDate;
    private  boolean isCurrentJob;
    private  String endDate;
    private  String additionalInfo;
}
