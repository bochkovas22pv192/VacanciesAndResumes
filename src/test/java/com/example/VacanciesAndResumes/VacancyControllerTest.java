//package com.example.VacanciesAndResumes;
//
//import com.example.VacanciesAndResumes.DTOs.ResumePostAnswerDTO;
//import com.example.VacanciesAndResumes.DTOs.VacancyDTO;
//import com.example.VacanciesAndResumes.repositories.VacancyRepository;
//import com.example.VacanciesAndResumes.services.VacancyService;
//import io.restassured.RestAssured;
//import org.hamcrest.MatcherAssert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class VacancyControllerTest {
//    @LocalServerPort
//    private Integer port;
//
//    @Autowired
//    VacancyService vacancyService;
//
//
//
//    @BeforeEach
//    void setUp() {
//        RestAssured.baseURI = "http://localhost:" + port;
//    }
//
//    @Test
//    void shouldCreateVacancy() {
//        String requestBody = "{\n" +
//                "\"grade\":\"Junior\",\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"vacanciesName\": \"Аналитик\",\n" +
//                "\"role\":\"Tech Solutions\",\n" +
//                "\"customerProject\":\"Проект C\",\n" +
//                "\"salary\":\"10000\",\n" +
//                "\"currency\":\"RUB\",\n" +
//                "\"description\":\"Анализ бизнес-процессов\"\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Vacancies/")
//                .then().statusCode(200)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO("success", "Успешно сохранено")));
//
//        MatcherAssert.assertThat(vacancyService.getVacancyAll().getLast(), equalTo( new VacancyDTO(
//                "Junior", "Россия", "Москва", "Москва", "Аналитик", "Tech Solutions", "Проект C",
//                "10000", "RUB", "Анализ бизнес-процессов"
//        )));
//    }
//
//    @Test
//    void shouldCGetTaskGradeEmptyException() {
//        String requestBody = "{\n" +
//                "\"grade\":\"\",\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"vacanciesName\": \"Аналитик\",\n" +
//                "\"role\":\"Tech Solutions\",\n" +
//                "\"customerProject\":\"Проект C\",\n" +
//                "\"salary\":\"10000\",\n" +
//                "\"currency\":\"RUB\",\n" +
//                "\"description\":\"Анализ бизнес-процессов\"\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Vacancies/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(),
//                "Введено недопустимое значения поля «Грэйд»")));
//    }
//
//    @Test
//    void shouldCGetTaskRoleEmptyException() {
//        String requestBody = "{\n" +
//                "\"grade\":\"Junior\",\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"vacanciesName\": \"Аналитик\",\n" +
//                "\"role\":\"\",\n" +
//                "\"customerProject\":\"Проект C\",\n" +
//                "\"salary\":\"10000\",\n" +
//                "\"currency\":\"RUB\",\n" +
//                "\"description\":\"Анализ бизнес-процессов\"\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Vacancies/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(),
//                "Введено недопустимое значения поля «Роль»")));
//    }
//
//
//    @Test
//    void shouldCGetSalaryFormatException() {
//        String requestBody = "{\n" +
//                "\"grade\":\"Junior\",\n" +
//                "\"countryName\":\"Россия\",\n" +
//                "\"regionName\":\"Москва\",\n" +
//                "\"cityName\":\"Москва\",\n" +
//                "\"vacanciesName\": \"Аналитик\",\n" +
//                "\"role\":\"Tech Solutions\",\n" +
//                "\"customerProject\":\"Проект C\",\n" +
//                "\"salary\":\"1000a0\",\n" +
//                "\"currency\":\"RUB\",\n" +
//                "\"description\":\"Анализ бизнес-процессов\"\n" +
//                "}";
//
//        ResumePostAnswerDTO result = given()
//                .contentType("application/json")
//                .body(requestBody)
//                .when()
//                .post("/api/v1/Vacancies/")
//                .then().statusCode(400)
//                .extract().body().as(ResumePostAnswerDTO.class);
//
//        MatcherAssert.assertThat(result, equalTo(new ResumePostAnswerDTO(HttpStatus.BAD_REQUEST.toString(),
//                "Введено недопустимое значения поля «Зарплата»")));
//    }
//}
