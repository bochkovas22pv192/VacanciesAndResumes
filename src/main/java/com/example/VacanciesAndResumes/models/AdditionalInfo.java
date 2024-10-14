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
public class AdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long additionalInfoId    ;

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column
    private boolean willingToRelocate ;

    @Column(length = 100)
    private String employmentType ;

    @Column
    private boolean willingToTravel  ;
}
