package com.example.VacanciesAndResumes.controllers;

import com.example.VacanciesAndResumes.DTOs.ResumeDTO;
import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/Personal_Info")
public class ResumeController {
    private final ResumeService service;


    @GetMapping("/")
    List<ResumeDTO> GetResumeAll() {
        return service.getResumeAll();
    }

    @PostMapping("/")
    ResumeAnswerDTO createResume(@RequestBody ResumeDTO resumeDTO) {
        return service.createResume(resumeDTO);
    }
}
