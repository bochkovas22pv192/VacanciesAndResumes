package com.example.VacanciesAndResumes;

import org.springframework.boot.SpringApplication;

public class TestVacanciesAndResumesApplication {

	public static void main(String[] args) {
		SpringApplication.from(VacanciesAndResumesApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
