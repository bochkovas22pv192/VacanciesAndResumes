package com.example.VacanciesAndResumes.controllers;

import com.example.VacanciesAndResumes.DTOs.ResumePostAnswerDTO;
import com.example.VacanciesAndResumes.Exceptions.Resume.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResumeControllerAdvice {
    @ExceptionHandler(LastNameWhitespaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO employeeNotFoundHandler(LastNameWhitespaceException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(FirstNameWhitespaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO firstNameWhitespaceHandler(FirstNameWhitespaceException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(MiddleNameWhitespaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO middleNameWhitespaceHandler(MiddleNameWhitespaceException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(DateOfBirthMinDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO dateOfBirthMinDateHandler(DateOfBirthMinDateException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskCountryEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO taskCountryEmptyHandler(TaskCountryEmptyException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskRegionEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO taskRegionEmptyHandler(TaskRegionEmptyException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskCityEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO taskCityEmptyHandler(TaskCityEmptyException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(MobilePhoneWhitespaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO mobilePhoneWhitespaceHandler(MobilePhoneWhitespaceException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskEmailEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO taskEmailEmptyHandler(TaskEmailEmptyException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskTelegramEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO taskTelegramEmptyHandler(TaskTelegramEmptyException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(WhatsAppFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO whatsAppFormatHandler(WhatsAppFormatException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(TaskDesiredPositionEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO taskDesiredPositionEmptyHandler(TaskDesiredPositionEmptyException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(StartDateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO startDateFormatHandler(StartDateFormatException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(EndDateFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResumePostAnswerDTO endDateFormatHandler(EndDateFormatException ex) {
        return new ResumePostAnswerDTO(
                HttpStatus.BAD_REQUEST.toString(),
                ex.getMessage()
        );
    }
}
