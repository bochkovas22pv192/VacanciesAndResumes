package com.example.VacanciesAndResumes.models;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Resume {
    private PersonalInfo personalInfo;
    private Contact contact;
    private Specialization specialization;
    private WorkExperience workExperience;
    private List<Language> languages;
    private AdditionalInfo additionalInfo;
    private List<Document> documents;
    private Education education;
    private CertificatesQualification certificatesQualification;

}
