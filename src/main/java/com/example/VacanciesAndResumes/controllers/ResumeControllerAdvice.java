package com.example.VacanciesAndResumes.controllers;

import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.exceptions.resume.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResumeControllerAdvice {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO badRequestHandler(BadRequestException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

}
