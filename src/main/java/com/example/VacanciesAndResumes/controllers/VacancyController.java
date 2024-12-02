package com.example.VacanciesAndResumes.controllers;


import com.example.VacanciesAndResumes.DTOs.*;
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
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {

    private final VacancyService service;

    @GetMapping
    List<VacancyDTO> getVacancyAll(@RequestParam(required=false) Map<String, String> queryParams){
        return service.getVacancyAll(queryParams);
    }

    @GetMapping("/statuses")
    ResumeGetStatusAnswerDTO getVacancyStatuses(){
        return service.getVacancyStatuses();
    }


    @PostMapping
    ResponseEntity<ResumeAnswerDTO> createVacancy(@RequestBody VacancyDTO vacancyDTO) {
        return new ResponseEntity<ResumeAnswerDTO>(service.createVacancy(vacancyDTO), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    ResponseEntity<ResumeAnswerDTO> updateVacancyStatus(@PathVariable("id") String id, @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, IOException {
        return new ResponseEntity<ResumeAnswerDTO>(service.updateVacancyPatch(UUID.fromString(id), jsonPatch), HttpStatus.OK) ;
    }

    @GetMapping(path = "/{vacancy_id}/comment")
    CommentVacancyGetDTO getCommentsVacancy (@PathVariable("vacancy_id") String vacancyId){
        return service.getCommentsForVacancy(UUID.fromString(vacancyId));
    }

    @PostMapping(path = "/{vacancy_id}/comment")
    CommentVacancyPostAnswerDTO createCommentVacancy (@PathVariable("vacancy_id") String vacancyId,
                                                      @RequestBody CommentVacancyPostDTO commentVacancyPostDTO) {
        return service.createCommentVacancy(UUID.fromString(vacancyId), commentVacancyPostDTO);
    }

    @PatchMapping(path = "/comment/{id}", consumes = "application/json-patch+json")
    CommentVacancyPostAnswerDTO patchCommentVacancyText (@PathVariable("id") String id, @RequestBody JsonPatch jsonPatch)
            throws JsonPatchException, IOException {
        return service.updateCommentVacancy(UUID.fromString(id), jsonPatch);
    }

    @DeleteMapping(path = "/comment/{id}")
    ResumeAnswerDTO deleteCommentVacancy (@PathVariable("id") String id){
        return service.deleteCommentVacancy(UUID.fromString(id));
    }

}
