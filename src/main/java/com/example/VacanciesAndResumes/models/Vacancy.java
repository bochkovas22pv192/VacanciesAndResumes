package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Vacancy extends PersistableEntity {

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

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

}
