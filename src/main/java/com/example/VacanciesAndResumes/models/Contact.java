package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContactID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="Personal_InfoID")
    private Candidate candidate;

    @Column(name = "MobilePhone", nullable = false)
    private String mobilePhone ;

    @Column(name = "Email", nullable = false)
    private String email ;

    @Column(name = "Telegram", nullable = false)
    private String telegram ;

    @Column(name = "WhatsApp")
    private String whatsApp;

    @Column(name = "VK")
    private String vk;

    @Column(name = "Habr")
    private String habr;

    @Column(name = "LinkedIn")
    private String linkedIn;

    @Column(name = "GitHub")
    private String gitHub;
}
