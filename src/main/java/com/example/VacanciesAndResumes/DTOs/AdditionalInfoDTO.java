package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AdditionalInfoDTO {
    private  boolean willingToRelocate;
    private  String employmentType;
    private  boolean willingToTravel;
}
