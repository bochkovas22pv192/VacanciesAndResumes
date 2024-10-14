package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Modifying
    @Query(value = "DELETE FROM personal_info", nativeQuery = true)
    void deleteRep();
}
