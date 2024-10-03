package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PersonalInfoDTO {
    private  String lastName;
    private  String firstName;
    private  String middleName;
    private  String dateOfBirth;
    private  Long age;
    private  String countryName;
    private  String regionName;
    private  String cityName;
    private  String citizenship;
    private  boolean workPermit;
}
