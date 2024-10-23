package com.example.VacanciesAndResumes.controllers;

import com.example.VacanciesAndResumes.DTOs.ResumeDTO;
import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.services.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/candidate")
public class ResumeController {
    private final ResumeService service;


    @GetMapping("/")
    List<ResumeDTO> getResumeAll() {
        return service.getResumeAll();
    }

    @PostMapping("/")
    ResponseEntity<ResumeAnswerDTO> createResume(@RequestBody ResumeDTO resumeDTO) {
        return new ResponseEntity<ResumeAnswerDTO>(service.createResume(resumeDTO), HttpStatus.CREATED) ;
    }
}
