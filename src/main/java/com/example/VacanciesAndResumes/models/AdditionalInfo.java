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

    @ManyToOne
    @JoinColumn(name="personal_info_id")
    private PersonalInfo personalInfo;

    @Column
    private boolean willingToRelocate ;

    @Column
    private String employmentType ;

    @Column
    private boolean willingToTravel  ;
}
