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
    private Candidate candidate;

    @Column
    private boolean educationLevel;

    @Column
    private boolean institution;

    @Column
    private boolean faculty;

    @Column
    private boolean specialization;

    @Column
    private Long graduationYear;
}
