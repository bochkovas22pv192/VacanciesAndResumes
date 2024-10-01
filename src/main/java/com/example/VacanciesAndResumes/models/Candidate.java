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
@Table(name = "Personal_Info")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Personal_InfoID", nullable = false)
    private Long id;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "MiddleName", nullable = false)
    private String middleName;

    @Column(name = "GenderName", nullable = false)
    private String genderName;

    @Column(name = "DateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "Age", nullable = false)
    private Long age;

    @Column(name = "CountryName", nullable = false)
    private Long countryName;

    @Column(name = "RegionName", nullable = false)
    private Long regionName;

    @Column(name = "CityName", nullable = false)
    private Long cityName;

    @Column(name = "Citizenship", nullable = false)
    private Long citizenship;

    @Column(name = "WorkPermit", nullable = false)
    private boolean workPermit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Candidate")
    @ToString.Exclude
    private Collection<Contact> contacts;

}
