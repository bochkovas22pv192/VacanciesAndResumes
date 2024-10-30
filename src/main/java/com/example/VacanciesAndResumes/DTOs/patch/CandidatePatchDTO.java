package com.example.VacanciesAndResumes.DTOs.patch;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidatePatchDTO {
    private  String lastName;
    private  String firstName;
    private  String middleName;
    private int gender;
    private  String birthDate;
    private  String country;
    private  String region;
    private  String city;
    private  String citizenship;
    private String status;
    private  boolean hasWorkPermit;
    private int relocate;
    private int travel;
    private LocalDateTime createdAt;
    private boolean docOffer;
    private boolean docScreen;
}
