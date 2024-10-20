package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_language")
public class Language extends PersistableEntity {

    @ManyToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(length = 3)
    private String language;

    @Column(length = 2)
    private String level;
}
