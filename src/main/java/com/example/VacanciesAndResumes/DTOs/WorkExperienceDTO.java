package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WorkExperienceDTO {
    private  String organizationName;
    private  String industry;
    private  String organizationWebsite;
    private  String companyCity;
    private  String position;
    private  String startDate;
    private  boolean isCurrentJob;
    private  String endDate;
    private  Long workDuration;
    private  String additionalInfo;
    private  String totalExperience;
}
