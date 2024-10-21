package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.models.Employment;
import com.example.VacanciesAndResumes.models.KeySkill;
import com.example.VacanciesAndResumes.repositories.EmploymentRepository;
import com.example.VacanciesAndResumes.repositories.KeySkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmploymentRepository employmentRepository, KeySkillRepository keySkillRepository) {
        Employment employment1 = Employment.builder().employmentName("Частичная занятость").candidates(Set.of()).build();
        Employment employment2 = Employment.builder().employmentName("Полная занятость").candidates(Set.of()).build();

        KeySkill keySkill1 = KeySkill.builder().keySkillName("Python").build();
        KeySkill keySkill2 = KeySkill.builder().keySkillName("C++").build();

        return args -> {
            log.info("Preloading " + employmentRepository.save(employment1));
            log.info("Preloading " + employmentRepository.save(employment2));

            log.info("Preloading " + keySkillRepository.save(keySkill1));
            log.info("Preloading " + keySkillRepository.save(keySkill2));
        };
    }
}
