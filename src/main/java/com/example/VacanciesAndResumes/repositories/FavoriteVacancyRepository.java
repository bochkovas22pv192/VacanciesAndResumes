package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.FavoriteVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavoriteVacancyRepository extends JpaRepository<FavoriteVacancy, UUID> {
}
