package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.CommentVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentVacancyRepository extends JpaRepository<CommentVacancy, UUID> {
}