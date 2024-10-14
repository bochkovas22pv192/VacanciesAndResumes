package com.example.VacanciesAndResumes.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resume {
    private Candidate candidate;
    private Contact contact;
    private Specialization specialization;
    private List<WorkExperience> workExperience;
    private List<Language> languages;
    private AdditionalInfo additionalInfo;
    private List<Document> documents;
    private List<Education> education;
    private List<CertificatesQualification> certificatesQualification;

}
