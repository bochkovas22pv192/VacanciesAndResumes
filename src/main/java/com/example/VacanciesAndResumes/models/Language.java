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
@Table(name = "tb_language")
public class Language extends PersistableEntity {

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(length = 3, nullable = false)
    private String language;

    @Column(length = 2, nullable = false)
    private String level;
}
