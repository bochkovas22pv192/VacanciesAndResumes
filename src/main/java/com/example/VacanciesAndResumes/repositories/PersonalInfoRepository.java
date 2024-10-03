package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {
}
