package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CertificatesQualificationDTO {
    private  String educationalInstitution;
    private  String organization;
    private  String specialization;
    private  int graduationYear;
}
