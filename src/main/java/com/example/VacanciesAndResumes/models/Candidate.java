package com.example.VacanciesAndResumes.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(exclude = {"contact", "specialization", "workExperiences", "languages",
        "educations", "certificatesQualifications", "documents"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Candidate extends PersistableEntity {

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

    @Column
    private boolean docOffer;

    @Column
    private boolean docScreen;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private Contact contact;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private Specialization specialization;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private List<WorkExperience> workExperiences;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private List<Language> languages;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private List<Education> educations;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private List<CertificatesQualification> certificatesQualifications;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
    @ToString.Exclude
    private List<Document> documents;

    @JsonManagedReference
    @ManyToMany(mappedBy = "candidates", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @ToString.Exclude
    List<Employment>  employments;

    @JsonManagedReference
    @ManyToMany(mappedBy = "candidates", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    List<KeySkill> keySkills;

    @JsonManagedReference
    @ManyToMany(mappedBy = "vacancyCandidates")
    List<Vacancy> vacancies;

    public Candidate(UUID id){this.setId(id);}

}
