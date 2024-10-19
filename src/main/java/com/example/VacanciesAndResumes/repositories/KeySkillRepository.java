package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.KeySkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KeySkillRepository extends JpaRepository<KeySkill, UUID> {
}
