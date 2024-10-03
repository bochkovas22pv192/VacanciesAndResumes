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
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long specializationId ;

    @ManyToOne
    @JoinColumn(name="personal_info_id")
    private PersonalInfo personalInfo;

    @Column(nullable = false)
    private String desiredPosition;

    @Column
    private String grade;

    @Column
    private String keySkills;

    @Column
    private double salary;

    @Column
    private String currency;
}
