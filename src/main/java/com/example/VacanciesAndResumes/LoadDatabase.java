package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.models.*;
import com.example.VacanciesAndResumes.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    EmploymentRepository employmentRepository;

    @Autowired
    KeySkillRepository keySkillRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    HandbookRepository handbookRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Bean
    CommandLineRunner initDatabase() {
        Employment employment1 = Employment.builder().employmentName("Частичная занятость").candidates(Set.of()).build();
        Employment employment2 = Employment.builder().employmentName("Полная занятость").candidates(Set.of()).build();

        KeySkill keySkill1 = KeySkill.builder().keySkillName("Python").build();
        KeySkill keySkill2 = KeySkill.builder().keySkillName("C++").build();

        Customer customer = Customer.builder().name("ТН").build();

        Handbook handbook1 = Handbook.builder().code("Resume Status").keyName("Offer").valueName("Оффер").build();
        Handbook handbook2 = Handbook.builder().code("Resume Status").keyName("Screening").valueName("Скрининг").build();

        Handbook handbook3 = Handbook.builder().code("Vacancy Status").keyName("true").valueName("Активная вакансия").build();
        Handbook handbook4 = Handbook.builder().code("Vacancy Status").keyName("false").valueName("Вакансия в архиве").build();

        Employee employee1 = Employee.builder().firstName("Иван").lastName("Иванов").email("ivan@gmail.com").build();

        return args -> {
            log.info("Preloading " + employmentRepository.save(employment1));
            log.info("Preloading " + employmentRepository.save(employment2));

            log.info("Preloading " + keySkillRepository.save(keySkill1));
            log.info("Preloading " + keySkillRepository.save(keySkill2));

            log.info("Preloading " + customerRepository.save(customer));

            log.info("Preloading " + handbookRepository.save(handbook1));
            log.info("Preloading " + handbookRepository.save(handbook2));

            log.info("Preloading " + handbookRepository.save(handbook3));
            log.info("Preloading " + handbookRepository.save(handbook4));

            log.info("Preloading " + employeeRepository.save(employee1));
        };
    }
}
