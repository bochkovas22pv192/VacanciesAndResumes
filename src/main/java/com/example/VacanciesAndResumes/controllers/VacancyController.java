package com.example.VacanciesAndResumes.controllers;


import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.VacancyDTO;
import com.example.VacanciesAndResumes.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {

    private final VacancyService service;

    @GetMapping
    List<VacancyDTO> getVacancyAll(){
        return service.getVacancyAll();
    }

    @PostMapping
    ResponseEntity<ResumeAnswerDTO> createResume(@RequestBody VacancyDTO vacancyDTO) {
        return new ResponseEntity<ResumeAnswerDTO>(service.createVacancy(vacancyDTO), HttpStatus.CREATED);
    }
}
