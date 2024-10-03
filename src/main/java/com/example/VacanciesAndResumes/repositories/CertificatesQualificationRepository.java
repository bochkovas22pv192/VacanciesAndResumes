package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.CertificatesQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificatesQualificationRepository extends JpaRepository<CertificatesQualification, Long> {
}
