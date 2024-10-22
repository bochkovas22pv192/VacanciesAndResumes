package com.example.VacanciesAndResumes.repositories;

import com.example.VacanciesAndResumes.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findByName(String name);
}
