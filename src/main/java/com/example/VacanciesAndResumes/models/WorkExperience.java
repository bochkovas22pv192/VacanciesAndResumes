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
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(length = 100)
    private String organizationName;

    @Column(length = 100)
    private String industry;

    @Column(length = 100)
    private String organizationWebsite ;

    @Column(length = 100)
    private String companyCity ;

    @Column
    private String position;

    @Column
    private LocalDate startDate;

    @Column
    private boolean isCurrentJob ;

    @Column
    private LocalDate endDate;

    @Lob
    @Column
    private String additionalInfo;
}
