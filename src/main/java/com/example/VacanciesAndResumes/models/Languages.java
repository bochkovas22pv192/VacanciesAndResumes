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
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long languagesId   ;

    @ManyToOne
    @JoinColumn(name="personal_info_id")
    private Candidate candidate;

    @Column
    private String language;

    @Column
    private String level;
}
