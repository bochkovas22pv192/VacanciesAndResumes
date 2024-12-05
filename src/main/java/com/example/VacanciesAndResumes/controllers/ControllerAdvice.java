package com.example.VacanciesAndResumes.controllers;

import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.exceptions.InternalServerErrorException;
import com.example.VacanciesAndResumes.exceptions.resume.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO badRequestHandler(BadRequestException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResumeAnswerDTO internalServerErrorHandler(InternalServerErrorException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ex.getMessage()
        );
    }

}
