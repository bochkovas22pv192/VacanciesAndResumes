package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "personal_info")
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long personalInfoId ;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String genderName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private Long age;

    @Column(nullable = false)
    private Long countryName;

    @Column(nullable = false)
    private Long regionName;

    @Column(nullable = false)
    private Long cityName;

    @Column(nullable = false)
    private Long citizenship;

    @Column(nullable = false)
    private boolean workPermit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    @ToString.Exclude
    private Collection<Contact> contacts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    @ToString.Exclude
    private Collection<Specialization> specializations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    @ToString.Exclude
    private Collection<WorkExperience> workExperiences;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    @ToString.Exclude
    private Collection<Language> languages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    @ToString.Exclude
    private Collection<AdditionalInfo> additionalInfos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    @ToString.Exclude
    private Collection<Education> educations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    @ToString.Exclude
    private Collection<CertificatesQualification> certificatesQualifications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalInfo")
    @ToString.Exclude
    private Collection<Document> documents;

}
