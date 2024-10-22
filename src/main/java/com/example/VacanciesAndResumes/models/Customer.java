package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@EqualsAndHashCode(exclude = "vacancies")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Customer extends PersistableEntity{
    @Column(nullable = false, length=100)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @ToString.Exclude
    private Collection<Vacancy> vacancies;
}
