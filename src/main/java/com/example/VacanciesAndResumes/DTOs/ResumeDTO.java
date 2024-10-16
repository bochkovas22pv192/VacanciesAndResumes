package com.example.VacanciesAndResumes.DTOs;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResumeDTO {
    private CandidateDTO candidate;
    private ContactDTO contact;
    private SpecializationDTO specialization;
    private List<WorkExperienceDTO> workExperiences;
    private List<LanguageDTO> languages;
    private AdditionalInfoDTO additionalInfo;
    private List<DocumentDTO> documents;
    private List<EducationDTO> educations;
    private List<CertificatesQualificationDTO> certificatesQualifications;

}
