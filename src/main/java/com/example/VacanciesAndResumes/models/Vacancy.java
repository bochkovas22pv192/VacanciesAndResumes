package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long vacancyId;

    @Column(nullable = false)
    private String grade;

    @Column
    private String countryName;

    @Column
    private String regionName;

    @Column
    private String cityName;

    @Column
    private String vacanciesName;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String customerProject;

    @Column
    private String salary;

    @Column
    private String currency;

    @Column
    private String description;
}
