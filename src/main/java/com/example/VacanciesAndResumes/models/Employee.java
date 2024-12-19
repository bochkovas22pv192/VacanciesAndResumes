package com.example.VacanciesAndResumes.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CommentVacancy> commentVacancies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Vacancy> vacancies;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<FavoriteVacancy> favoriteVacancies;

}
