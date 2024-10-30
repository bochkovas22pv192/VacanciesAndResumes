package com.example.VacanciesAndResumes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Education extends PersistableEntity {

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(length = 50, nullable = false)
    private String educationLevel;

    @Column(length = 100, nullable = false)
    private String institution;

    @Column(length = 100)
    private String faculty;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = false)
    private int graduationYear;
}
