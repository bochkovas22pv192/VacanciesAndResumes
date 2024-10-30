package com.example.VacanciesAndResumes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class WorkExperience extends PersistableEntity {

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(length = 100, nullable = false)
    private String organizationName;

    @Column(length = 100)
    private String industry;

    @Column(length = 100)
    private String website ;

    @Column(length = 100)
    private String city ;

    @Column(length = 100, nullable = false)
    private String roleName;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private boolean isCurrentJob;

    @Column
    private LocalDate endDate;

    @Lob
    @Column
    private String additionalInfo;
}
