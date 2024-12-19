package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class FavoriteVacancy extends PersistableEntity {

    @ManyToOne
    @JoinColumn(name="vacancy_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Vacancy vacancy;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employee employee;

}
