//package com.example.VacanciesAndResumes;
//
//import com.example.VacanciesAndResumes.DTOs.*;
//import com.example.VacanciesAndResumes.mappers.ResumeMapper;
//import com.example.VacanciesAndResumes.repositories.*;
//import com.example.VacanciesAndResumes.services.ResumeService;
//import io.restassured.RestAssured;
//import org.hamcrest.MatcherAssert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ResumeControllerTest {
//
//    @LocalServerPort
//    private Integer port;
//
//    @Autowired
//    ResumeService resumeService;
//
//    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//    @BeforeEach
//    void setUp() {
//        RestAssured.baseURI = "http://localhost:" + port;
//    }
//
//    @Test
//    void shouldCreateResume() {
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(200)
//                .extract().body().as(ResumePostAnswerDTO.class);
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO("success", "Успешно сохранено")));
//        ResumeDTO resume = resumeService.getResumeAll().getLast();
//        MatcherAssert.assertThat(resume.getPersonalInfo(),
//                equalTo(new PersonalInfoDTO("Иванов", "Иван", "Иванович", "М", "1990.01.01", 34,
//                        "Россия", "Москва", "Москва", "РФ", true)));
//
//        MatcherAssert.assertThat(resume.getContact(),
//                equalTo(new ContactDTO("+79999999999", "ivanov@mail.ru", "@ivanov", "+79999999999", "vk.com/ivanov",
//                        "habr.com/ivanov", "linkedin.com/in/ivanov", "github.com/ivanov")));
//
//        MatcherAssert.assertThat(resume.getSpecialization(),
//                equalTo(new SpecializationDTO("Разработчик", "Middle", "Python, SQL, Docker",
//                        100000, "RUB")));
//
//        MatcherAssert.assertThat(resume.getWorkExperience(),
//                equalTo(new WorkExperienceDTO("Tech Corp", "IT", "techcorp.com", "Москва", "Программист",
//                        "12.07.2015", false, "23.08.2020", 5, "Работа в крупной компании", 5)));
//
//        MatcherAssert.assertThat(resume.getLanguages(),
//                equalTo(List.of(new LanguageDTO("Английский", "Advanced"), new LanguageDTO("Немецкий", "Intermediate"))));
//
//        MatcherAssert.assertThat(resume.getAdditionalInfo(),
//                equalTo(new AdditionalInfoDTO(true, "Полная занятость", true)));
//
//        MatcherAssert.assertThat(resume.getDocuments(),
//                equalTo(List.of(new DocumentDTO("aaaaaaaa"), new DocumentDTO("bbbbbbb"))));
//
//        MatcherAssert.assertThat(resume.getAdditionalInfo(),
//                equalTo(new AdditionalInfoDTO(true, "Полная занятость", true)));
//
//        MatcherAssert.assertThat(resume.getEducation(),
//                equalTo(new EducationDTO("Высшее", "МГУ", "Факультет ВМиК",
//                        "Программная инженерия", 2019)));
//
//        MatcherAssert.assertThat(resume.getCertificatesQualification(),
//                equalTo(new CertificatesQualificationDTO("Skillbox", "ООО Skillbox",
//                        "Python разработка", 2023)));
//    }
//
//    @Test
//    void shouldGetLastNameWhitespaceException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"   \",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void shouldGetFirstNameWhitespaceException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"   \",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void shouldGetMiddleNameWhitespaceException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"  \",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void shouldGetDateOfBirthMinDateException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"2225.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void TaskCountryEmptyException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void TaskRegionEmptyException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void TaskCityEmptyException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void MobilePhoneWhitespaceException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"  \",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void TaskEmailEmptyException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void TaskTelegramEmptyException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void WhatsAppFormatException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+7a999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void TaskDesiredPositionEmptyException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//
//    @Test
//    void StartDateFormatException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.a07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//    @Test
//    void EndDateFormatException(){
//        String requestBody = "{\n" +
//                "\"personalInfo\":{\n" +
//                "\"lastName\":\"Иванов\",\n" +
//                "\"firstName\":\"Иван\",\n" +
//                "\"middleName\":\"Иванович\",\n" +
//                "\"genderName\":\"М\",\n" +
//                "\"dateOfBirth\":\"1990.01.01\",\n" +
//                "\"age\":34,\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"citizenship\":\"РФ\",\n" +
//                "\"workPermit\":true\n" +
//                "},\n" +
//                "\"contact\":{\n" +
//                "\"mobilePhone\":\"+79999999999\",\n" +
//                "\"email\":\"ivanov@mail.ru\",\n" +
//                "\"telegram\":\"@ivanov\",\n" +
//                "\"whatsapp\":\"+79999999999\",\n" +
//                "\"vk\":\"vk.com/ivanov\",\n" +
//                "\"habr\":\"habr.com/ivanov\",\n" +
//                "\"linkedIn\":\"linkedin.com/in/ivanov\",\n" +
//                "\"gitHub\":\"github.com/ivanov\"\n" +
//                "},\n" +
//                "\"specialization\":{\n" +
//                "\"desiredPosition\":\"Разработчик\",\n" +
//                "\"grade\":\"Middle\",\n" +
//                "\"keySkills\":\"Python, SQL, Docker\",\n" +
//                "\"salary\":100000,\n" +
//                "\"currency\":\"RUB\"\n" +
//                "},\n" +
//                "\"workExperience\":{\n" +
//                "\"organizationName\":\"Tech Corp\",\n" +
//                "\"industry\":\"IT\",\n" +
//                "\"organizationWebsite\":\"techcorp.com\",\n" +
//                "\"companyCity\":\"Москва\",\n" +
//                "\"position\":\"Программист\",\n" +
//                "\"startDate\":\"12.07.2015\",\n" +
//                "\"isCurrentCob\":false,\n" +
//                "\"endDate\":\"23.a08.2020\",\n" +
//                "\"workDuration\":5,\n" +
//                "\"additionalInfo\":\"Работа в крупной компании\",\n" +
//                "\"totalExperience\":5\n" +
//                "},\n" +
//                "\"languages\":[\n" +
//                "{\n" +
//                "\"language\":\"Английский\",\n" +
//                "\"level\":\"Advanced\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"language\":\"Немецкий\",\n" +
//                "\"level\":\"Intermediate\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"additionalInfo\":{\n" +
//                "\"willingToRelocate\":true,\n" +
//                "\"employmentType\":\"Полная занятость\",\n" +
//                "\"willingToTravel\":true\n" +
//                "},\n" +
//                "\"documents\":[{\n" +
//                "\"document\":\"aaaaaaaa\"\n" +
//                "},\n" +
//                "{\n" +
//                "\"document\":\"bbbbbbb\"\n" +
//                "}\n" +
//                "],\n" +
//                "\"education\":{\n" +
//                "\"educationLevel\":\"Высшее\",\n" +
//                "\"institution\":\"МГУ\",\n" +
//                "\"faculty\":\"Факультет ВМиК\",\n" +
//                "\"specialization\":\"Программная инженерия\",\n" +
//                "\"graduationYear\":2019\n" +
//                "},\n" +
//                "\"certificatesQualification\":{\n" +
//                "\"educationalInstitution\":\"Skillbox\",\n" +
//                "\"organization\":\"ООО Skillbox\",\n" +
//                "\"specialization\":\"Python разработка\",\n" +
//                "\"graduationYear\":2023\n" +
//                "}\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Personal_Info/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(), "Неверно заполнено поле")));
//    }
//
//
//
//}
