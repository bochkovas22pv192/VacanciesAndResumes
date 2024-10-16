package com.example.VacanciesAndResumes.controllers;

import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.Exceptions.Resume.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResumeControllerAdvice {
    @ExceptionHandler(LastNameWhitespaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO employeeNotFoundHandler(LastNameWhitespaceException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(FirstNameWhitespaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO firstNameWhitespaceHandler(FirstNameWhitespaceException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(MiddleNameWhitespaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO middleNameWhitespaceHandler(MiddleNameWhitespaceException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(DateOfBirthMinDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO dateOfBirthMinDateHandler(DateOfBirthMinDateException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskCountryEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO taskCountryEmptyHandler(TaskCountryEmptyException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskRegionEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO taskRegionEmptyHandler(TaskRegionEmptyException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskCityEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO taskCityEmptyHandler(TaskCityEmptyException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(MobilePhoneWhitespaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO mobilePhoneWhitespaceHandler(MobilePhoneWhitespaceException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskEmailEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO taskEmailEmptyHandler(TaskEmailEmptyException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskTelegramEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO taskTelegramEmptyHandler(TaskTelegramEmptyException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(WhatsAppFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO whatsAppFormatHandler(WhatsAppFormatException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskDesiredPositionEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO taskDesiredPositionEmptyHandler(TaskDesiredPositionEmptyException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(StartDateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO startDateFormatHandler(StartDateFormatException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(EndDateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumeAnswerDTO endDateFormatHandler(EndDateFormatException ex) {
        return new ResumeAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }
}
