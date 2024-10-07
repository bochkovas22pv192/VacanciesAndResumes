package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalInfoDTO {
    private  String lastName;
    private  String firstName;
    private  String middleName;
    private String genderName;
    private  String dateOfBirth;
    private  int age;
    private  String countryName;
    private  String regionName;
    private  String cityName;
    private  String citizenship;
    private  boolean workPermit;
}
