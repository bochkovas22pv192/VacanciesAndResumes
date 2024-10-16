package com.example.VacanciesAndResumes.controllers;

import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.exceptions.vacancies.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VacancyControllerAdvice {
    @ExceptionHandler(TaskGradeEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO taskGradeEmptyHandler(TaskGradeEmptyException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskRoleEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO taskRoleEmptyHandler(TaskRoleEmptyException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(SalaryFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO salaryFormatHandler(SalaryFormatException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }
}
