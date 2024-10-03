package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {
}

