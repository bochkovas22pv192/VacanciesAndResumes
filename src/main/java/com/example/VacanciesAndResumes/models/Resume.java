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
    private List<WorkExperience> workExperiences;
    private List<Language> languages;
    private List<Document> documents;
    private List<Education> educations;
    private List<CertificatesQualification> certificatesQualifications;

}
