package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.repositories.*;
import com.example.VacanciesAndResumes.services.ResumeService;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResumeControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    ResumeMapper resumeMapper;
    @Autowired
    ResumeService resumeService;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
            "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
            "\"gitHub\":\"github.com/ivanov\"\n" +
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
            "\"startDate\":\"12.07.2015\",\n" +
            "\"isCurrentCob\":false,\n" +
            "\"endDate\":\"23.08.2020\",\n" +
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
            "\"employmentType\":\"Полная занятость\",\n" +
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
            "\"graduationYear\":2023\n" +
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
        ResumeDTO resume = resumeService.getResumeAll().getLast();
        MatcherAssert.assertThat(resume.getPersonalInfo(),
                equalTo(new PersonalInfoDTO("Иванов", "Иван", "Иванович", "М", "1990.01.01", 34,
                        "Россия", "Москва", "Москва", "РФ", true)));

        MatcherAssert.assertThat(resume.getContact(),
                equalTo(new ContactDTO("+79999999999", "ivanov@mail.ru", "@ivanov", "+79999999999", "vk.com/ivanov",
                        "habr.com/ivanov", "linkedin.com/in/ivanov", "github.com/ivanov")));

        MatcherAssert.assertThat(resume.getSpecialization(),
                equalTo(new SpecializationDTO("Разработчик", "Middle", "Python, SQL, Docker",
                        100000, "RUB")));

        MatcherAssert.assertThat(resume.getWorkExperience(),
                equalTo(new WorkExperienceDTO("Tech Corp", "IT", "techcorp.com", "Москва", "Программист",
                        "12.07.2015", false, "23.08.2020", 5, "Работа в крупной компании", 5)));

        MatcherAssert.assertThat(resume.getLanguages(),
                equalTo(List.of(new LanguageDTO("Английский", "Advanced"), new LanguageDTO("Немецкий", "Intermediate"))));

        MatcherAssert.assertThat(resume.getAdditionalInfo(),
                equalTo(new AdditionalInfoDTO(true, "Полная занятость", true)));

        MatcherAssert.assertThat(resume.getDocuments(),
                equalTo(List.of(new DocumentDTO("aaaaaaaa"), new DocumentDTO("bbbbbbb"))));

        MatcherAssert.assertThat(resume.getAdditionalInfo(),
                equalTo(new AdditionalInfoDTO(true, "Полная занятость", true)));

        MatcherAssert.assertThat(resume.getEducation(),
                equalTo(new EducationDTO("Высшее", "МГУ", "Факультет ВМиК",
                        "Программная инженерия", 2019)));

        MatcherAssert.assertThat(resume.getCertificatesQualification(),
                equalTo(new CertificatesQualificationDTO("Skillbox", "ООО Skillbox",
                        "Python разработка", 2023)));
    }
}
