package com.example.VacanciesAndResumes.controllers;

import com.example.VacanciesAndResumes.DTOs.ResumePostAnswerDTO;
import com.example.VacanciesAndResumes.Exceptions.Vacancies.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VacancyControllerAdvice {
    @ExceptionHandler(TaskGradeEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO taskGradeEmptyHandler(TaskGradeEmptyException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskRoleEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO taskRoleEmptyHandler(TaskRoleEmptyException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(SalaryFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO salaryFormatHandler(SalaryFormatException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }
}
