package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResumeDTO {
    private CandidateDTO personalInfo;
    private ContactDTO contact;
    private SpecializationDTO specialization;
    private List<WorkExperienceDTO> workExperience;
    private List<LanguageDTO> languages;
    private AdditionalInfoDTO additionalInfo;
    private List<DocumentDTO> documents;
    private List<EducationDTO> education;
    private List<CertificatesQualificationDTO> certificatesQualification;

}
