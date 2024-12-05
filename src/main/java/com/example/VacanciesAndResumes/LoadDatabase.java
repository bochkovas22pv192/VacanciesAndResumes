package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.models.*;
import com.example.VacanciesAndResumes.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    CommentVacancyRepository commentVacancyRepository;


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

        Employee employee1 = Employee.builder().firstName("Иван")
                .lastName("Иванов").email("ivan@gmail.com").favoriteVacancies(new LinkedHashSet<Vacancy>()).build();
        Employee employee2 = Employee.builder().firstName("Петр")
                .lastName("Петров").email("petr@gmail.com").favoriteVacancies(new LinkedHashSet<Vacancy>()).build();

        Vacancy vacancy1 = new Vacancy(customer, employee1, "owner and fav", "Java разработчик",
                "Нужен хороший разраб", 10000, "RUB", "Junior", "Белорусь",
                "Москва", "Москва", true, LocalDateTime.now(), List.of(), new LinkedHashSet<Employee>(List.of(employee1, employee2)));

        Vacancy vacancy2 = new Vacancy(customer, employee1, "owner", "Java разработчик",
                "Нужен хороший разраб", 100, "RUB", "Middle", "Белорусь",
                "Москва", "Белгород", true, LocalDateTime.now(), List.of(), new LinkedHashSet<Employee>(List.of(employee2)));

        Vacancy vacancy3 = new Vacancy(customer, employee2, "fav", "Java разработчик",
                "Нужен хороший разраб", 2000, "RUB", "Senior", "Россия",
                "Москва", "Москва", true, LocalDateTime.now(), List.of(), new LinkedHashSet<Employee>(List.of(employee1)));

        Vacancy vacancy4 = new Vacancy(customer, employee2, "non", "Java разработчик",
                "Нужен хороший разраб", 5000, "RUB", "Junior", "Россия",
                "Москва", "Белгород", true, LocalDateTime.now(), List.of(), new LinkedHashSet<Employee>());



        CommentVacancy commentVacancy1 = new CommentVacancy(vacancy1, employee1, "Отличная вакансия!",
                false, LocalDateTime.now(), LocalDateTime.now());

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
            log.info("Preloading " + employeeRepository.save(employee2));

            log.info("Preloading " + vacancyRepository.save(vacancy1));
            log.info("Preloading " + vacancyRepository.save(vacancy2));
            log.info("Preloading " + vacancyRepository.save(vacancy3));
            log.info("Preloading " + vacancyRepository.save(vacancy4));
            log.info("Preloading " + commentVacancyRepository.save(commentVacancy1));
        };
    }
}
