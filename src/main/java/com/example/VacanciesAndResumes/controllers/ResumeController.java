package com.example.VacanciesAndResumes.controllers;

import com.example.VacanciesAndResumes.DTOs.ResumeDTO;
import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.ResumeGetStatusAnswerDTO;
import com.example.VacanciesAndResumes.exceptions.resume.BadRequestException;
import com.example.VacanciesAndResumes.models.Candidate;
import com.example.VacanciesAndResumes.services.ResumeService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@RequestMapping("api/candidate")
public class ResumeController {
    private final ResumeService service;

    @GetMapping
    List<ResumeDTO> getResumeAll() {
        return service.getResumeAll();
    }

    @GetMapping("/statuses")
    ResumeGetStatusAnswerDTO getResumeStatus() {
        return service.getStatusList();
    }

    @PostMapping
    ResponseEntity<ResumeAnswerDTO> createResume(@RequestBody ResumeDTO resumeDTO) {
        return new ResponseEntity<>(service.createResume(resumeDTO), HttpStatus.CREATED) ;
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    ResponseEntity<ResumeAnswerDTO> updateResumeStatus(@PathVariable("id") String id, @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, IOException {
        String jsonPatchString = jsonPatch.toString().split("value: \"")[1];
        String newStatus = jsonPatchString.substring(0, jsonPatchString.length()-2);
        Candidate candidate = service.getCandidateByID(UUID.fromString(id));
        if (newStatus.equals("Offer") && !candidate.isDocOffer()){
            throw new BadRequestException("Изменения статуса на \"Offer\" возможно только при наличии документов на оффер");
        }
        if (newStatus.equals("Screening") && !candidate.isDocScreen()){
            throw new BadRequestException("Изменения статуса на \"Screening\" возможно только при наличии документов скриннинга");
        }
        if (newStatus.equals("Hired") && !candidate.getStatus().equals("Оффер")){
            throw new BadRequestException("Изменения статуса на \"Hired\" возможно только после предоставления оффера");
        }
        return new ResponseEntity<>(service.updateCandidatePatch(UUID.fromString(id), jsonPatch), HttpStatus.OK) ;
    }
}
