package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long workExperienceId  ;

    @ManyToOne
    @JoinColumn(name="personal_info_id")
    private Candidate candidate;

    @Column
    private String organizationName;

    @Column
    private String industry;

    @Column
    private String organizationWebsite ;

    @Column
    private String companyCity ;

    @Column
    private String position;

    @Column
    private LocalDate startData;

    @Column
    private LocalDate endData;

    @Column
    private boolean isCurrentJob ;

    @Column
    private String additionalInfo ;
}
