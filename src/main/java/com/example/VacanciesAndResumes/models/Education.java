package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long educationId;

    @ManyToOne
    @JoinColumn(name="personal_info_id")
    private PersonalInfo personalInfo;

    @Column
    private String educationLevel;

    @Column
    private String institution;

    @Column
    private String faculty;

    @Column
    private String specialization;

    @Column
    private int graduationYear;
}
