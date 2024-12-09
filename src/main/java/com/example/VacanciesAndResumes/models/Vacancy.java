package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Vacancy extends PersistableEntity {

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

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
    @ToString.Exclude
    private Collection<CommentVacancy> commentVacancies;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "favorite_vacancy",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> favoriteEmployees;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "candidates_vacancies",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id")
    )
    private Set<Candidate> vacancyCandidates;

}
