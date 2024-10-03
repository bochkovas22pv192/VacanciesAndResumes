package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CertificatesQualificationDTO {
    private  String educationalInstitution;
    private  String organization;
    private  String specialization;
    private  Long graduationYearn;
}
