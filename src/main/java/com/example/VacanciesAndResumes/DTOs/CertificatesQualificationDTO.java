package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CertificatesQualificationDTO {
    @JsonProperty(value = "educational_institution")
    private  String educationalInstitution;
    private  String organization;
    private  String specialization;
    @JsonProperty(value = "graduation_yearn")
    private  int graduationYear;
}
