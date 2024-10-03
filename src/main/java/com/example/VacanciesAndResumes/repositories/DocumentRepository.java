package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
