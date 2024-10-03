package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
