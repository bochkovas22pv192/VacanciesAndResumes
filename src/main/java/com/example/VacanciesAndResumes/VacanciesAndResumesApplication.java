package com.example.VacanciesAndResumes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class VacanciesAndResumesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacanciesAndResumesApplication.class, args);
	}

}
