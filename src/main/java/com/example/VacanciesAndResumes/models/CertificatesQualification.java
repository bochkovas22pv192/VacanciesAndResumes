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
public class CertificatesQualification extends PersistableEntity {

    @ManyToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(length = 100, nullable = false)
    private String educationalInstitution ;

    @Column(length = 100, nullable = false)
    private String organization;

    @Column(length = 100, nullable = false)
    private String specialization;

    @Column(nullable = false)
    private int graduationYear;
}
