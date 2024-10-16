package com.example.VacanciesAndResumes.exceptions.resume;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
