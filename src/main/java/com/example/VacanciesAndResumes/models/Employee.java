package com.example.VacanciesAndResumes.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"favoriteVacancies", "vacancies", "commentVacancies"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Employee extends PersistableEntity {
    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(length = 50)
    private String middleName;

    @Column(nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @ToString.Exclude
    private Collection<CommentVacancy> commentVacancies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @ToString.Exclude
    private Collection<Vacancy> vacancies;

    @ManyToMany(mappedBy = "favoriteEmployees", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Vacancy> favoriteVacancies;

}
