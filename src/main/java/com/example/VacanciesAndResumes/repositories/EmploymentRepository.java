package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, UUID> {
    Employment findByEmploymentName(String employmentName);
}
