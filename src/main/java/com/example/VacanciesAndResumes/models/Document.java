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
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long documentId     ;

    @ManyToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Lob
    @Column
    private byte[] document ;
}
