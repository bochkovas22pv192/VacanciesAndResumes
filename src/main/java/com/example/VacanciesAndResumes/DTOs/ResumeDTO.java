package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResumeDTO {
    private PersonalInfoDTO personalInfo;
    private ContactDTO contact;
    private SpecializationDTO specialization;
    private WorkExperienceDTO workExperience;
    private List<LanguageDTO> languages;
    private AdditionalInfoDTO additionalInfo;
    private List<DocumentDTO> documents;
    private EducationDTO education;
    private CertificatesQualificationDTO certificatesQualification;

}
