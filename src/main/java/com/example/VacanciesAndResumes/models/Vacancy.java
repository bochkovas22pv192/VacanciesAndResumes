package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Vacancy extends PersistableEntity {

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Employee employee;

    @Column(length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String roleName;

    @Lob
    @Column
    private String description;

    @Column
    private int salary;

    @Column(length = 3)
    private String currency;

    @Column(nullable = false, length = 20)
    private String grade;

    @Column(nullable = false, length = 20)
    private String country;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacancy")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CommentVacancy> commentVacancies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacancy")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FavoriteVacancy> favoriteVacancies;

    @ManyToMany
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(
            name = "candidates_vacancies",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id")
    )
    private List<Candidate> vacancyCandidates;

}
