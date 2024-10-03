package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}

