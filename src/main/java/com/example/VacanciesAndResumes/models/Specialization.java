package com.example.VacanciesAndResumes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Specialization extends PersistableEntity {

    @JsonBackReference
    @ToString.Exclude
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
