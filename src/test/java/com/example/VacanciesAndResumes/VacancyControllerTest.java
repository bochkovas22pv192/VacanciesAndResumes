package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.DTOs.CustomerDTO;
import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.ResumeGetStatusAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.VacancyDTO;
import com.example.VacanciesAndResumes.models.Handbook;
import com.example.VacanciesAndResumes.repositories.HandbookRepository;
import com.example.VacanciesAndResumes.repositories.VacancyRepository;
import com.example.VacanciesAndResumes.services.VacancyService;
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

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VacancyControllerTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    VacancyService vacancyService;

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    HandbookRepository handbookRepository;

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
    void shouldCreateVacancy() {
        String requestBody = "{\n" +
                "    \"title\":\"Программист Java в проект\",    \n" +
                "    \"role_name\":\"Java разработчик\", \n" +
                "    \"description\":\"Нужен хороший разраб\",\n" +
                "    \"salary\":10000,\n" +
                "    \"currency\":\"RUB\",\n" +
                "    \"grade\":\"Junior\",\n" +
                "    \"country\":\"Россия\",\n" +
                "    \"region\":\"Москва\",\n" +
                "    \"city\":\"Москва\",\n" +
                "    \"customer\":{\n" +
                "    \"name\":\"ТН\"\n" +
                "    }\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/vacancy")
                .then().statusCode(201)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO("success", "Успешно сохранено")));

        MatcherAssert.assertThat(vacancyService.getVacancyAll().getLast(), equalTo( new VacancyDTO("Программист Java в проект",
                "Java разработчик", "Нужен хороший разраб", 10000, "RUB", "Junior", "Россия", "Москва",
                "Москва", new CustomerDTO("ТН")

        )));
    }

    @Test
    void shouldGetVacancyStatuses(){
        Handbook handbook3 = Handbook.builder().code("Vacancy Status").keyName("true").valueName("Активная вакансия").build();
        Handbook handbook4 = Handbook.builder().code("Vacancy Status").keyName("false").valueName("Вакансия в архиве").build();
        handbookRepository.save(handbook3);
        handbookRepository.save(handbook4);
        ResumeGetStatusAnswerDTO result = given()
                .contentType("application/json")
                .when()
                .get("/api/vacancy/statuses")
                .then().statusCode(200)
                .extract().body().as(ResumeGetStatusAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(vacancyService.getVacancyStatuses()));
    }

    @Test
    void shouldChangeVacancyStatus(){
        String requestBody = "{\n" +
                "    \"title\":\"Программист Java в проект\",    \n" +
                "    \"role_name\":\"Java разработчик\", \n" +
                "    \"description\":\"Нужен хороший разраб\",\n" +
                "    \"salary\":10000,\n" +
                "    \"currency\":\"RUB\",\n" +
                "    \"grade\":\"Junior\",\n" +
                "    \"country\":\"Россия\",\n" +
                "    \"region\":\"Москва\",\n" +
                "    \"city\":\"Москва\",\n" +
                "    \"customer\":{\n" +
                "    \"name\":\"ТН\"\n" +
                "    }\n" +
                "}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/vacancy")
                .then().statusCode(201);
        requestBody = "[\n" +
                "{\"op\":\"replace\",\"path\":\"/is_active\",\"value\":false} \n" +
                "]";

        UUID id = vacancyRepository.findAll().getLast().getId();
        ResumeAnswerDTO result = given()
                .contentType("application/json-patch+json")
                .body(requestBody)
                .when()
                .patch("/api/vacancy/" + id.toString())
                .then().statusCode(200)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO("success", "Успешно изменено")));
        MatcherAssert.assertThat(vacancyRepository.findAll().getLast().isActive(), equalTo(false));
    }

    @Test
    void shouldCGetTaskGradeEmptyException() {
        String requestBody = "{\n" +
                "    \"title\":\"Программист Java в проект\",    \n" +
                "    \"role_name\":\"Java разработчик\", \n" +
                "    \"description\":\"Нужен хороший разраб\",\n" +
                "    \"salary\":10000,\n" +
                "    \"currency\":\"RUB\",\n" +
                "    \"grade\":\"\",\n" +
                "    \"country\":\"Россия\",\n" +
                "    \"region\":\"Москва\",\n" +
                "    \"city\":\"Москва\",\n" +
                "    \"customer\":{\n" +
                "    \"name\":\"ТН\"\n" +
                "    }\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/vacancy")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(),
                "Введено недопустимое значения поля «Грэйд»")));
    }

    @Test
    void shouldCGetTaskRoleEmptyException() {
        String requestBody = "{\n" +
                "    \"title\":\"Программист Java в проект\",    \n" +
                "    \"role_name\":\"\", \n" +
                "    \"description\":\"Нужен хороший разраб\",\n" +
                "    \"salary\":10000,\n" +
                "    \"currency\":\"RUB\",\n" +
                "    \"grade\":\"Junior\",\n" +
                "    \"country\":\"Россия\",\n" +
                "    \"region\":\"Москва\",\n" +
                "    \"city\":\"Москва\",\n" +
                "    \"customer\":{\n" +
                "    \"name\":\"ТН\"\n" +
                "    }\n" +
                "}";

        ResumeAnswerDTO result = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/vacancy")
                .then().statusCode(400)
                .extract().body().as(ResumeAnswerDTO.class);

        MatcherAssert.assertThat(result, equalTo(new ResumeAnswerDTO(HttpStatus.BAD_REQUEST.toString(),
                "Введено недопустимое значения поля «Роль»")));
    }

}
