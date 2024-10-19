package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, UUID> {
}
