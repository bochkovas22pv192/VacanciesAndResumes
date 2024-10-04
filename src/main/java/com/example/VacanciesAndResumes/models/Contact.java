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
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long contactId ;

    @OneToOne
    @JoinColumn(name="personal_info_id")
    private PersonalInfo personalInfo;

    @Column(nullable = false)
    private String mobilePhone ;

    @Column(nullable = false)
    private String email ;

    @Column(nullable = false)
    private String telegram ;

    @Column
    private String whatsapp;

    @Column
    private String vk;

    @Column
    private String habr;

    @Column
    private String linkedIn;

    @Column
    private String gitHub;
}
