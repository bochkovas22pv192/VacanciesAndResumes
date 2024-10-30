package com.example.VacanciesAndResumes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Contact extends PersistableEntity {

    @JsonBackReference
    @ToString.Exclude
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
