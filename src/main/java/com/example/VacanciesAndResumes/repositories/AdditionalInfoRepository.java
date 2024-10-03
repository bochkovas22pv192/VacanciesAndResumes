package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.AdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {
}
