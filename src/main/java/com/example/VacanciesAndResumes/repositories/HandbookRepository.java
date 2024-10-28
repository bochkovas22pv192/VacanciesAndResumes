package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Handbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HandbookRepository extends JpaRepository<Handbook, UUID> {
    List<Handbook> findByCode(String code);
}
