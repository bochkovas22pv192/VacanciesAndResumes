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

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(nullable = false, length = 100)
    private String roleName;

    @Column(length = 20, nullable = false)
    private String grade;

    @Column
    private int salary;

    @Column(length = 3)
    private String currency;
}
