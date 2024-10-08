package com.example.VacanciesAndResumes.controllers;


import com.example.VacanciesAndResumes.DTOs.ResumePostAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.VacancyDTO;
import com.example.VacanciesAndResumes.services.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/Vacancies")
public class VacancyController {

    private final VacancyService service;

    @GetMapping("/")
    List<VacancyDTO> getVacancyAll(){
        return service.getVacancyAll();
    }

    @PostMapping("/")
    ResumePostAnswerDTO createResume(@RequestBody VacancyDTO vacancyDTO) {
        return service.createVacancy(vacancyDTO);
    }
}
