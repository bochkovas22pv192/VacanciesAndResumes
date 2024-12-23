package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.exceptions.resume.BadRequestException;
import com.example.VacanciesAndResumes.mappers.VacancyMapper;
import com.example.VacanciesAndResumes.models.*;
import com.example.VacanciesAndResumes.repositories.*;
import com.example.VacanciesAndResumes.services.VacancyService;
import com.jayway.jsonpath.Criteria;
import io.restassured.RestAssured;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hamcrest.MatcherAssert;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VacancyControllerTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    VacancyService vacancyService;

    @Autowired
    VacancyMapper vacancyMapper;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    HandbookRepository handbookRepository;

    @Autowired
    CommentVacancyRepository commentVacancyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FavoriteVacancyRepository favoriteVacancyRepository;

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
        commentVacancyRepository.deleteAll();
        customerRepository.deleteAll();
        vacancyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void TestServicePost(){
        Employee employee1 = Employee.builder().firstName("Иван")
                .lastName("Иванов").email("ivan@gmail.com").favoriteVacancies(new ArrayList<FavoriteVacancy>()).build();

        employeeRepository.save(employee1);

        UUID employeeId = employeeRepository.findAll().getLast().getId();

        Customer customer = Customer.builder().name("ТН").build();
        customerRepository.save(customer);

        vacancyService.createVacancy(new VacancyDTO("Программист Java в проект",
                        "Java разработчик", "Нужен хороший разраб", 10000, "RUB", "Junior", "Россия", "Москва",
                        "Москва", employeeId.toString(), new CustomerDTO("ТН")));
        Vacancy vacancy = vacancyRepository.findAll().getLast();
        MatcherAssert.assertThat(vacancyMapper.vacancyToVacancyDTO(vacancyRepository.findAll().getLast()),
                equalTo( new VacancyDTO("Программист Java в проект",
                "Java разработчик", "Нужен хороший разраб", 10000, "RUB",
                        "Junior", "Россия", "Москва",
                "Москва",employeeId.toString(), new CustomerDTO("ТН")

        )));
    }

    @Test
    void shouldCreateVacancy() {
        Customer customer = Customer.builder().name("ТН").build();
        customerRepository.save(customer);

        Employee employee1 = Employee.builder().firstName("Иван")
                .lastName("Иванов").email("ivan@gmail.com").favoriteVacancies(new ArrayList<FavoriteVacancy>()).build();

        employeeRepository.save(employee1);
        UUID employeeId = employeeRepository.findAll().getLast().getId();

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
                "    \"owner_id\":\""+ employeeId +"\",\n" +
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

        MatcherAssert.assertThat(vacancyMapper.vacancyToVacancyDTO(vacancyRepository.findAll().getLast()), equalTo( new VacancyDTO("Программист Java в проект",
                "Java разработчик", "Нужен хороший разраб", 10000, "RUB", "Junior", "Россия", "Москва",
                "Москва", employeeId.toString(), new CustomerDTO("ТН")

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
        Customer customer = Customer.builder().name("ТН").build();

        Employee employee1 = Employee.builder().firstName("Иван").lastName("Иванов").email("ivan@gmail.com").build();

        Vacancy vacancy1 = new Vacancy(customer, employee1, "non", "Java разработчик",
                "Нужен хороший разраб", 5000, "RUB", "Junior", "Россия",
                "Москва", "Белгород", true, LocalDateTime.now(),
                List.of(), new ArrayList<FavoriteVacancy>(), new ArrayList<Candidate>());


        CommentVacancy commentVacancy1 = new CommentVacancy(vacancy1, employee1, "Отличная вакансия!",
                false, LocalDateTime.now(), LocalDateTime.now());

        customerRepository.save(customer);
        employeeRepository.save(employee1);
        vacancyRepository.save(vacancy1);
        commentVacancyRepository.save(commentVacancy1);

        UUID vacancyId = vacancyRepository.findAll().getLast().getId();

        String requestBody = "[\n" +
                "{\"op\":\"replace\",\"path\":\"/is_active\",\"value\":false} \n" +
                "]";

        ResumeAnswerDTO result = given()
                .contentType("application/json-patch+json")
                .body(requestBody)
                .when()
                .patch("/api/vacancy/" + vacancyId.toString())
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

    @Test
    void shouldCGetCommentVacancy() {
        Customer customer = Customer.builder().name("ТН").build();

        Employee employee1 = Employee.builder().firstName("Иван").lastName("Иванов").email("ivan@gmail.com").build();

        Vacancy vacancy1 = new Vacancy(customer, employee1, "non", "Java разработчик",
                "Нужен хороший разраб", 5000, "RUB", "Junior", "Россия",
                "Москва", "Белгород", true, LocalDateTime.now(),
                List.of(), new ArrayList<FavoriteVacancy>(), new ArrayList<Candidate>());


        CommentVacancy commentVacancy1 = new CommentVacancy(vacancy1, employee1, "Отличная вакансия!",
                false, LocalDateTime.now(), LocalDateTime.now());

        customerRepository.save(customer);
        employeeRepository.save(employee1);
        vacancyRepository.save(vacancy1);
        commentVacancyRepository.save(commentVacancy1);

        UUID vacancyId = vacancyRepository.findAll().getLast().getId();


        CommentVacancyGetDTO result = given()
                .contentType("application/json")
                .when()
                .get("/api/vacancy/" + vacancyId + "/comment")
                .then().statusCode(200)
                .extract().body().as(CommentVacancyGetDTO.class);

        MatcherAssert.assertThat(result, equalTo(new CommentVacancyGetDTO("success", "Данные об истории взаимодействий получены.",
               List.of(new CommentVacancyDTO(result.getComments().getFirst().getId(), "Отличная вакансия!",
                       "Иван Иванов", result.getComments().getFirst().getTimestamp()))
        )));
    }

    private void generateDataForSearchTest(){
        Customer customer = Customer.builder().name("ТН").build();

        Employee employee1 = Employee.builder().firstName("Иван")
                .lastName("Иванов").email("ivan@gmail.com").favoriteVacancies(new ArrayList<FavoriteVacancy>()).build();
        Employee employee2 = Employee.builder().firstName("Петр")
                .lastName("Петров").email("petr@gmail.com").favoriteVacancies(new ArrayList<FavoriteVacancy>()).build();

        Vacancy vacancy1 = new Vacancy(customer, employee1, "owner and fav", "Java разработчик",
                "Нужен хороший разраб", 10000, "RUB", "Junior", "Белорусь",
                "Москва", "Москва", false,
                LocalDateTime.now(), List.of(), new ArrayList<FavoriteVacancy>(), new ArrayList<Candidate>());

        Vacancy vacancy2 = new Vacancy(customer, employee1, "owner", "Python разработчик",
                "Нужен хороший разраб", 100, "RUB", "Middle", "Белорусь",
                "Белгородская область", "Белгород", true, LocalDateTime.now(), List.of(),
                new ArrayList<FavoriteVacancy>(), new ArrayList<Candidate>());

        Vacancy vacancy3 = new Vacancy(customer, employee2, "fav", "Java разработчик",
                "Нужен хороший разраб", 2000, "RUB", "Senior", "Россия",
                "Москва", "Москва", true, LocalDateTime.now(), List.of(),
                new ArrayList<FavoriteVacancy>(), new ArrayList<Candidate>());

        Vacancy vacancy4 = new Vacancy(customer, employee2, "non", "Java разработчик",
                "Нужен хороший разраб", 5000, "RUB", "Junior", "Россия",
                "Белгородская область", "Белгород", true, LocalDateTime.now(), List.of(),
                new ArrayList<FavoriteVacancy>(), new ArrayList<Candidate>());

        FavoriteVacancy favoriteVacancy1 = FavoriteVacancy.builder().vacancy(vacancy1).employee(employee1).build();
        FavoriteVacancy favoriteVacancy2 = FavoriteVacancy.builder().vacancy(vacancy1).employee(employee2).build();
        FavoriteVacancy favoriteVacancy3 = FavoriteVacancy.builder().vacancy(vacancy2).employee(employee2).build();
        FavoriteVacancy favoriteVacancy4 = FavoriteVacancy.builder().vacancy(vacancy3).employee(employee1).build();

        customerRepository.save(customer);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        List<Vacancy> vacancies = List.of(vacancy1, vacancy2, vacancy3, vacancy4);
        vacancyRepository.saveAll(vacancies);
        favoriteVacancyRepository.saveAll(List.of(favoriteVacancy1, favoriteVacancy2, favoriteVacancy3, favoriteVacancy4));
    }

    @Test
    void shouldGetVacanciesAll(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);

        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "fav", "Java разработчик", "Senior", true,  "ТН", 0, true)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "non", "Java разработчик", "Junior", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(2), equalTo(
                new VacancyGetDTO(result.getResult().get(2).getId(), "owner", "Python разработчик", "Middle", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(3), equalTo(
                new VacancyGetDTO(result.getResult().get(3).getId(), "owner and fav", "Java разработчик", "Junior", false,  "ТН", 0, true)
        ));

    }

    @Test
    void shouldGetVacanciesByCountry(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("country", "Россия")
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);

        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "fav", "Java разработчик", "Senior", true,  "ТН", 0, true)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "non", "Java разработчик", "Junior", true,  "ТН", 0, false)
        ));

    }

    @Test
    void shouldGetVacanciesByRegion(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("region", "Белгородская область")
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);


        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "non", "Java разработчик", "Junior", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "owner", "Python разработчик", "Middle", true,  "ТН", 0, false)
        ));
    }

    @Test
    void shouldGetVacanciesByCity(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("city", "Белгород")
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);


        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "non", "Java разработчик", "Junior", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "owner", "Python разработчик", "Middle", true,  "ТН", 0, false)
        ));

    }

    @Test
    void shouldGetVacanciesByRole(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("role", "Python разработчик")
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);


        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "owner", "Python разработчик", "Middle", true,  "ТН", 0, false)
        ));
    }

    @Test
    void shouldGetVacanciesByStatus(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("status", false)
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);

        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "owner and fav", "Java разработчик", "Junior", false,  "ТН", 0, true)
        ));

    }

    @Test
    void shouldGetVacanciesByGrade(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("grade", "Senior Junior")
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);

        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "fav", "Java разработчик", "Senior", true,  "ТН", 0, true)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "non", "Java разработчик", "Junior", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(2), equalTo(
                new VacancyGetDTO(result.getResult().get(2).getId(), "owner and fav", "Java разработчик", "Junior", false,  "ТН", 0, true)
        ));

    }

    @Test
    void shouldGetVacanciesBySalaryFrom(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("salary_from", 4000)
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);


        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "non", "Java разработчик", "Junior", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "owner and fav", "Java разработчик", "Junior", false,  "ТН", 0, true)
        ));
    }

    @Test
    void shouldGetVacanciesBySalaryTo(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("salary_to", 6000)
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);

        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "fav", "Java разработчик", "Senior", true,  "ТН", 0, true)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "non", "Java разработчик", "Junior", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(2), equalTo(
                new VacancyGetDTO(result.getResult().get(2).getId(), "owner", "Python разработчик", "Middle", true,  "ТН", 0, false)
        ));

    }

    @Test
    void shouldGetVacanciesByFavs(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("favs", true)
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);


        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "fav", "Java разработчик", "Senior", true,  "ТН", 0, true)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "owner and fav", "Java разработчик", "Junior", false,  "ТН", 0, true)
        ));

    }

    @Test
    void shouldGetVacanciesByMine(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("mine", true)
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);


        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "owner", "Python разработчик", "Middle", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "owner and fav", "Java разработчик", "Junior", false,  "ТН", 0, true)
        ));

    }

    @Test
    void shouldGetVacanciesSortSalary(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("sort", "salary")
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);


        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "owner", "Python разработчик", "Middle", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "fav", "Java разработчик", "Senior", true,  "ТН", 0, true)
        ));

        MatcherAssert.assertThat(result.getResult().get(2), equalTo(
                new VacancyGetDTO(result.getResult().get(2).getId(), "non", "Java разработчик", "Junior", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(3), equalTo(
                new VacancyGetDTO(result.getResult().get(3).getId(), "owner and fav", "Java разработчик", "Junior", false,  "ТН", 0, true)
        ));
    }

    @Test
    void shouldGetVacanciesSortByDate(){
        generateDataForSearchTest();
        UUID employeeId = employeeRepository.findAll().getFirst().getId();
        VacancyGetAnswerDTO result = given()
                .contentType("application/json")
                .queryParam("owner_id", employeeId.toString())
                .queryParam("sort", "createdAt")
                .when()
                .get("/api/vacancy")
                .then().statusCode(200)
                .extract().body().as(VacancyGetAnswerDTO.class);

        MatcherAssert.assertThat(result.getResult().get(0), equalTo(
                new VacancyGetDTO(result.getResult().get(0).getId(), "owner and fav", "Java разработчик", "Junior", false,  "ТН", 0, true)
        ));

        MatcherAssert.assertThat(result.getResult().get(1), equalTo(
                new VacancyGetDTO(result.getResult().get(1).getId(), "owner", "Python разработчик", "Middle", true,  "ТН", 0, false)
        ));

        MatcherAssert.assertThat(result.getResult().get(2), equalTo(
                new VacancyGetDTO(result.getResult().get(2).getId(), "fav", "Java разработчик", "Senior", true,  "ТН", 0, true)
        ));

        MatcherAssert.assertThat(result.getResult().get(3), equalTo(
                new VacancyGetDTO(result.getResult().get(3).getId(), "non", "Java разработчик", "Junior", true,  "ТН", 0, false)
        ));
    }

    @Test
    void shouldAddToFavs(){
        Customer customer = Customer.builder().name("ТН").build();

        Employee employee1 = Employee.builder().firstName("Сидор")
                .lastName("Сидоров").email("ivan@gmail.com").favoriteVacancies(new ArrayList<FavoriteVacancy>()).build();

        Vacancy vacancy1 = new Vacancy(customer, employee1, "TEST", "Java разработчик",
                "Нужен хороший разраб", 10000, "RUB", "Junior", "Белорусь",
                "Москва", "Москва", false,
                LocalDateTime.now(), List.of(), new ArrayList<FavoriteVacancy>(), new ArrayList<Candidate>());

        customerRepository.save(customer);
        employeeRepository.save(employee1);
        vacancyRepository.save(vacancy1);

        UUID employeeId = employeeRepository.findAll().getLast().getId();
        UUID vacancyId = vacancyRepository.findAll().getLast().getId();

        String requestBody = "{\n" +
                "    \"employee_id\": \""+ employeeId + "\",\n" +
                "    \"vacancy_id\": \""+ vacancyId +"\"\n" +
                "}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/vacancy/add-favs")
                .then().statusCode(201);

        FavoriteVacancy favoriteVacancy = favoriteVacancyRepository.findAll().getLast();
        MatcherAssert.assertThat(favoriteVacancy.getVacancy().getId(), equalTo(vacancyId));
        MatcherAssert.assertThat(favoriteVacancy.getEmployee().getId(), equalTo(employeeId));
    }

    @Test
    void shouldAddDeleteFromFavs(){
        Customer customer = Customer.builder().name("ТН").build();

        Employee employee1 = Employee.builder().firstName("Сидор")
                .lastName("Сидоров").email("ivan@gmail.com").favoriteVacancies(new ArrayList<FavoriteVacancy>()).build();

        Vacancy vacancy1 = new Vacancy(customer, employee1, "TEST", "Java разработчик",
                "Нужен хороший разраб", 10000, "RUB", "Junior", "Белорусь",
                "Москва", "Москва", false,
                LocalDateTime.now(), List.of(), new ArrayList<FavoriteVacancy>(), new ArrayList<Candidate>());

        FavoriteVacancy favoriteVacancy1 = new FavoriteVacancy(vacancy1, employee1);

        customerRepository.save(customer);
        employeeRepository.save(employee1);
        vacancyRepository.save(vacancy1);
        favoriteVacancyRepository.save(favoriteVacancy1);

        UUID employeeId = employeeRepository.findAll().getLast().getId();
        UUID vacancyId = vacancyRepository.findAll().getLast().getId();

        String requestBody = "{\n" +
                "    \"employee_id\": \""+ employeeId + "\",\n" +
                "    \"vacancy_id\": \""+ vacancyId +"\"\n" +
                "}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .delete("/api/vacancy/delete-favs")
                .then().statusCode(200);

        List<FavoriteVacancy> favoriteVacancies = favoriteVacancyRepository.findAll();
        MatcherAssert.assertThat(favoriteVacancies.isEmpty(), equalTo(true));
    }

}
