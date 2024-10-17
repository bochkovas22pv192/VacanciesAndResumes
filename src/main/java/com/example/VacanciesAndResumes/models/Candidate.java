package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false, length=50)
    private String lastName;

    @Column(nullable = false, length=50)
    private String firstName;

    @Column(length=50)
    private String middleName;

    @Column
    private int gender;

    @Column
    private LocalDate birthDate;

    @Column(nullable = false, length=20)
    private String country;

    @Column(nullable = false, length=100)
    private String region;

    @Column(nullable = false, length=100)
    private String city;

    @Column(length=100)
    private String citizenship;

    @Column(nullable = false, length=20)
    private String status;

    @Column
    private boolean hasWorkPermit;

    @Column(nullable = false)
    private int relocate;

    @Column(nullable = false)
    private int travel;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private Specialization specialization;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private Collection<WorkExperience> workExperiences;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private Collection<Language> languages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private Collection<Education> educations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private Collection<CertificatesQualification> certificatesQualifications;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private Collection<Document> documents;

}
