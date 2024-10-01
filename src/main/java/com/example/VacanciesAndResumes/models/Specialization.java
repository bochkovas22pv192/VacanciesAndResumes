package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SpecializationID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="Personal_InfoID")
    private Candidate candidate;

    @Column(name = "DesiredPosition", nullable = false)
    private String desiredPosition;

    @Column(name = "Grade")
    private String grade;

    @Column(name = "KeySkills")
    private String keySkills;

    @Column(name = "Salary")
    private double salary;

    @Column(name = "Currency")
    private String currency;
}
