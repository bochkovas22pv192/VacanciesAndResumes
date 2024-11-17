package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.CommentVacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentVacancyRepository extends JpaRepository<CommentVacancy, UUID> {
}
