package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
}
