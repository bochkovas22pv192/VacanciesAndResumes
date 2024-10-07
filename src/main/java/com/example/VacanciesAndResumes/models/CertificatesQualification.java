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
public class CertificatesQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long certificatesQualificationId;

    @ManyToOne
    @JoinColumn(name="personal_info_id")
    private PersonalInfo personalInfo;

    @Column
    private String educationalInstitution ;

    @Column
    private String organization;

    @Column
    private String specialization;

    @Column
    private int graduationYear ;
}
