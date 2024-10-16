package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.repositories.*;
import com.example.VacanciesAndResumes.services.ResumeService;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.PostgreSQLContainer;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResumeControllerTest {

    @Autowired
    ResumeService resumeService;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @LocalServerPort
    private Integer port;


    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }


    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @Test
    void shouldCreateResume() {
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";
        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(200)
                .extract().body().as(ResumeAnswerDTO.class);
        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO("success", "Успешно сохранено")));
        ResumeDTO resume = resumeService.getResumeAll().getLast();
        MatcherAssert.assertThat(resume.getCandidate(),
                equalTo(new CandidateDTO("Иванов", "Иван", "Иванович", 1, "1990-01-01",
                        "Россия", "Москва", "Москва", "РФ", true)));

        MatcherAssert.assertThat(resume.getContact(),
                equalTo(new ContactDTO("+79999999999", "ivanov@mail.ru", "@ivanov", "+79999999999", "vk.com/ivanov",
                        "habr.com/ivanov", "linkedin.com/in/ivanov", "github.com/ivanov")));

        MatcherAssert.assertThat(resume.getSpecialization(),
                equalTo(new SpecializationDTO("Разработчик", "Middle", "Python, SQL, Docker",
                        100000, "RUB")));

        MatcherAssert.assertThat(resume.getWorkExperiences().getLast(),
                equalTo(new WorkExperienceDTO("Tech Corp", "IT", "techcorp.com",
                        "Москва", "Программист", "2015-07-15", false,
                        "2020-08-28", "Работа в крупной компании")));

        MatcherAssert.assertThat(resume.getLanguages(),
                equalTo(List.of(new LanguageDTO("Английский", "Advanced"), new LanguageDTO("Немецкий", "Intermediate"))));

        MatcherAssert.assertThat(resume.getAdditionalInfo(),
                equalTo(new AdditionalInfoDTO(true, "Полная занятость", true)));

        MatcherAssert.assertThat(resume.getDocuments(),
                equalTo(List.of(new DocumentDTO("aaaaaaaa"), new DocumentDTO("bbbbbbb"))));

        MatcherAssert.assertThat(resume.getAdditionalInfo(),
                equalTo(new AdditionalInfoDTO(true, "Полная занятость", true)));

        MatcherAssert.assertThat(resume.getEducations().getLast(),
                equalTo(new EducationDTO("Высшее", "МГУ", "Факультет ВМиК",
                        "Программная инженерия", 2019)));

        MatcherAssert.assertThat(resume.getCertificatesQualifications().getLast(),
                equalTo(new CertificatesQualificationDTO("Skillbox", "ООО Skillbox",
                        "Python разработка", 2023)));
    }

    @Test
    void shouldGetLastNameWhitespaceException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Фамилия\"")));
    }

    @Test
    void shouldGetFirstNameWhitespaceException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Имя\"")));
    }

    @Test
    void shouldGetMiddleNameWhitespaceException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Отчество\"")));
    }

    @Test
    void shouldGetDateOfBirthMinDateException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"2222-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Дата рождения\"")));
    }

    @Test
    void TaskCountryEmptyException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Страна\"")));
    }

    @Test
    void TaskRegionEmptyException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Регион\"")));
    }

    @Test
    void TaskCityEmptyException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Город\"")));
    }

    @Test
    void MobilePhoneWhitespaceException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \" \",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Мобильный телефон\"")));
    }

    @Test
    void TaskEmailEmptyException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Электронная почта\"")));
    }

    @Test
    void TaskTelegramEmptyException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Телеграм\"")));
    }

    @Test
    void WhatsAppFormatException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+799999aa999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Ватсап\"")));
    }

    @Test
    void TaskDesiredPositionEmptyException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Желаемая позиция\"")));
    }


    @Test
    void StartDateFormatException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-aa\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-28\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Дата начала\"")));
    }

    @Test
    void EndDateFormatException(){
        String requestBody = "{\n" +
                " \n" +
                "  \"candidate\": {\n" +
                "    \"last_name\": \"Иванов\",\n" +
                "    \"first_name\": \"Иван\",\n" +
                "    \"middle_name\": \"Иванович\",\n" +
                "    \"gender\": 1,\n" +
                "    \"birth_date\": \"1990-01-01\",\n" +
                "    \"country\": \"Россия\",\n" +
                "    \"region\": \"Москва\",\n" +
                "    \"city\": \"Москва\",\n" +
                "    \"citizenship\": \"РФ\",\n" +
                "    \"has_workpermit\": true\n" +
                "  },\n" +
                " \n" +
                "  \"contact\": {\n" +
                "    \"mobile_phone\": \"+79999999999\",\n" +
                "    \"email\": \"ivanov@mail.ru\",\n" +
                "    \"telegram\": \"@ivanov\",\n" +
                "    \"whatsapp\": \"+79999999999\",\n" +
                "    \"vk\": \"vk.com/ivanov\",\n" +
                "    \"habr\": \"habr.com/ivanov\",\n" +
                "    \"linkedin\": \"linkedin.com/in/ivanov\",\n" +
                "    \"github\": \"github.com/ivanov\"\n" +
                "  },\n" +
                "\"specialization\": {\n" +
                "    \"desired_position\": \"Разработчик\",\n" +
                "    \"grade\": \"Middle\",\n" +
                "    \"key_skills\": \"Python, SQL, Docker\",\n" +
                "    \"salary\": 100000,\n" +
                "    \"currency\": \"RUB\"\n" +
                "  },\n" +
                "\"work_experience\": [\n" +
                "{\n" +
                "    \"organization_name\": \"Tech Corp\",\n" +
                "    \"industry\": \"IT\",\n" +
                "    \"organization_website\": \"techcorp.com\",\n" +
                "    \"company_city\": \"Москва\",\n" +
                "    \"position\": \"Программист\",\n" +
                "    \"start_data\": \"2015-07-15\",\n" +
                "    \"is_current_job\": false,\n" +
                "    \"end_data\": \"2020-08-a8\",\n" +
                "    \"additional_info\": \"Работа в крупной компании\"\n" +
                "  }\n" +
                "],\n" +
                "\"language\": [\n" +
                "    {\n" +
                "      \"language\": \"Английский\",\n" +
                "      \"level\": \"Advanced\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"language\": \"Немецкий\",\n" +
                "      \"level\": \"Intermediate\"\n" +
                "    }\n" +
                "  ],\n" +
                "\"additional_info\": \n" +
                "{\n" +
                "    \"willing_to_relocate\": true,\n" +
                "    \"employment_type\": \"Полная занятость\",\n" +
                "    \"willing_to_travel\": true\n" +
                "  },\n" +
                "\"document\": [\n" +
                "{\n" +
                "    \"document_path\": \"aaaaaaaa\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"document_path\": \"bbbbbbb\"\n" +
                "  }\n" +
                "],\n" +
                "\"education\": [\n" +
                "{\n" +
                "     \"education_level\": \"Высшее\",\n" +
                "    \"institution\": \"МГУ\",\n" +
                "    \"faculty\": \"Факультет ВМиК\",\n" +
                "    \"specialization\": \"Программная инженерия\",\n" +
                "     \"graduation_year\": 2019\n" +
                "  }\n" +
                "],\n" +
                " \"certificate_qualification\": [\n" +
                "{\n" +
                "    \"educational_institution\": \"Skillbox\",\n" +
                "    \"organization\": \"ООО Skillbox\",\n" +
                "    \"specialization\": \"Python разработка\",\n" +
                "    \"graduation_yearn\": 2023\n" +
                "}\n" +
                "]\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле \"Дата окончания\"")));
    }



}
