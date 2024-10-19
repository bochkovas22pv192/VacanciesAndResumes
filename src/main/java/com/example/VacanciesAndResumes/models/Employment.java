package com.example.VacanciesAndResumes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Employment extends PersistableEntity {
    @Column(nullable = false, length=50)
    private String employmentName;

    @ManyToMany
    Set<Candidate> candidates;
}
