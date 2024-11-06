package com.example.VacanciesAndResumes.controllers;


import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.ResumeGetStatusAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.VacancyDTO;
import com.example.VacanciesAndResumes.exceptions.resume.BadRequestException;
import com.example.VacanciesAndResumes.models.Candidate;
import com.example.VacanciesAndResumes.services.VacancyService;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {

    private final VacancyService service;

    @GetMapping
    List<VacancyDTO> getVacancyAll(){
        return service.getVacancyAll();
    }

    @GetMapping("/statuses")
    ResumeGetStatusAnswerDTO getVacancyStatuses(){
        return service.getVacancyStatuses();
    }


    @PostMapping
    ResponseEntity<ResumeAnswerDTO> createResume(@RequestBody VacancyDTO vacancyDTO) {
        return new ResponseEntity<ResumeAnswerDTO>(service.createVacancy(vacancyDTO), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    ResponseEntity<ResumeAnswerDTO> updateResumeStatus(@PathVariable("id") String id, @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, IOException {
        return new ResponseEntity<ResumeAnswerDTO>(service.updateVacancyPatch(UUID.fromString(id), jsonPatch), HttpStatus.OK) ;
    }
}
