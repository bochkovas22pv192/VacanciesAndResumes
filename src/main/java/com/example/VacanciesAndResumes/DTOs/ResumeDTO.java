package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResumeDTO {
    private CandidateDTO candidate;
    private ContactDTO contact;
    private SpecializationDTO specialization;
    @JsonProperty(value = "work_experience")
    private List<WorkExperienceDTO> workExperiences;
    @JsonProperty(value = "language")
    private List<LanguageDTO> languages;
    @JsonProperty(value = "document")
    private List<DocumentDTO> documents;
    @JsonProperty(value = "education")
    private List<EducationDTO> educations;
    @JsonProperty(value = "certificate_qualification")
    private List<CertificatesQualificationDTO> certificatesQualifications;

}
