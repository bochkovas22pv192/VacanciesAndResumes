package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.DTOs.ResumePostAnswerDTO;
import com.example.VacanciesAndResumes.repositories.*;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResumeControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    AdditionalInfoRepository additionalInfoRepository;
    @Autowired
    CertificatesQualificationRepository certificatesQualificationRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    PersonalInfoRepository personalInfoRepository;
    @Autowired
    SpecializationRepository specializationRepository;
    @Autowired
    WorkExperienceRepository workExperienceRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    String requestBody = "{\n" +
            "\"personalInfo\":{\n" +
            "\"lastName\":\"Иванов\",\n" +
            "\"firstName\":\"Иван\",\n" +
            "\"middleName\":\"Иванович\",\n" +
            "\"genderName\":\"М\",\n" +
            "\"dateOfBirth\":\"1990.01.01\",\n" +
            "\"age\":34,\n" +
            "\"countryName\":\"Россия\",\n" +
            "\"regionName\":\"Москва\",\n" +
            "\"cityName\":\"Москва\",\n" +
            "\"citizenship\":\"РФ\",\n" +
            "\"workPermit\":true\n" +
            "},\n" +
            "\"contact\":{\n" +
            "\"mobilePhone\":\"+79999999999\",\n" +
            "\"email\":\"ivanov@mail.ru\",\n" +
            "\"telegram\":\"@ivanov\",\n" +
            "\"whatsapp\":\"+79999999999\",\n" +
            "\"vk\":\"vk.com/ivanov\",\n" +
            "\"habr\":\"habr.com/ivanov\",\n" +
            "\"linkedin\":\"linkedin.com/in/ivanov\",\n" +
            "\"github\":\"github.com/ivanov\"\n" +
            "},\n" +
            "\"specialization\":{\n" +
            "\"desiredPosition\":\"Разработчик\",\n" +
            "\"grade\":\"Middle\",\n" +
            "\"keySkills\":\"Python, SQL, Docker\",\n" +
            "\"salary\":100000,\n" +
            "\"currency\":\"RUB\"\n" +
            "},\n" +
            "\"workExperience\":{\n" +
            "\"organizationName\":\"Tech Corp\",\n" +
            "\"industry\":\"IT\",\n" +
            "\"organizationWebsite\":\"techcorp.com\",\n" +
            "\"companyCity\":\"Москва\",\n" +
            "\"position\":\"Программист\",\n" +
            "\"startData\":\"2015.01.01\",\n" +
            "\"isCurrentCob\":false,\n" +
            "\"eneData\":\"2020.01.01\",\n" +
            "\"workDuration\":5,\n" +
            "\"additionalInfo\":\"Работа в крупной компании\",\n" +
            "\"totalExperience\":5\n" +
            "},\n" +
            "\"languages\":[\n" +
            "{\n" +
            "\"language\":\"Английский\",\n" +
            "\"level\":\"Advanced\"\n" +
            "},\n" +
            "{\n" +
            "\"language\":\"Немецкий\",\n" +
            "\"level\":\"Intermediate\"\n" +
            "}\n" +
            "],\n" +
            "\"additionalInfo\":{\n" +
            "\"willingToRelocate\":true,\n" +
            "\"employmentType\":\"Частичная занятость\",\n" +
            "\"willingToTravel\":true\n" +
            "},\n" +
            "\"documents\":[{\n" +
            "\"document\":\"aaaaaaaa\"\n" +
            "},\n" +
            "{\n" +
            "\"document\":\"bbbbbbb\"\n" +
            "}\n" +
            "],\n" +
            "\"education\":{\n" +
            "\"educationLevel\":\"Высшее\",\n" +
            "\"institution\":\"МГУ\",\n" +
            "\"faculty\":\"Факультет ВМиК\",\n" +
            "\"specialization\":\"Программная инженерия\",\n" +
            "\"graduationYear\":2019\n" +
            "},\n" +
            "\"certificatesQualification\":{\n" +
            "\"educationalInstitution\":\"Skillbox\",\n" +
            "\"organization\":\"ООО Skillbox\",\n" +
            "\"specialization\":\"Python разработка\",\n" +
            "\"graduationYearn\":2023\n" +
            "}\n" +
            "}";

    @Test
    void shouldCreateResume() {
        ResumePostAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/Personal_Info/")
                .then().statusCode(200)
                .extract().body().as(ResumePostAnswerDTO.class);
        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO("success", "Успешно сохранено")));

    }
}
