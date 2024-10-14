package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateDTO {
    private  String lastName;
    private  String firstName;
    private  String middleName;
    private int gender;
    private  String birthDate;
    private  String country;
    private  String region;
    private  String city;
    private  String citizenship;
    private  boolean workPermit;
}
