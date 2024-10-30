package com.example.VacanciesAndResumes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(exclude = {"candidates"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Employment extends PersistableEntity {
    @Column(nullable = false, length=50)
    private String employmentName;

    @JsonBackReference
    @ToString.Exclude
    @ManyToMany
    Set<Candidate> candidates;
}
