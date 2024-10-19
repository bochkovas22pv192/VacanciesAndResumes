package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Contact extends PersistableEntity {

    @OneToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Column(nullable = false, length = 15)
    private String mobilePhone ;

    @Column(nullable = false)
    private String email ;

    @Column(nullable = false, length = 33)
    private String telegram ;

    @Column(length = 15)
    private String whatsapp;

    @Column(length = 100)
    private String vk;

    @Column(length = 100)
    private String habr;

    @Column(length = 100)
    private String linkedin;

    @Column(length = 100)
    private String github;
}
