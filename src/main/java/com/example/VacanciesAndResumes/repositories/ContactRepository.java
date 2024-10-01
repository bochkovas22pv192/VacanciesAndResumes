package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
