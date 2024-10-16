package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long vacancyId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String roleName;

    @Lob
    @Column
    private String description;

    @Column(nullable = false)
    private int salary;

    @Column(nullable = false, length = 20)
    private String country;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false)
    private String customerProject;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean isActive;


}
