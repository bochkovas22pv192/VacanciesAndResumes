package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.CommentVacancy;
import com.example.VacanciesAndResumes.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentVacancyRepository extends JpaRepository<CommentVacancy, UUID> {
    List<CommentVacancy> findByVacancy(Vacancy vacancy);
}
