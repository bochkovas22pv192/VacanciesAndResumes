package com.example.VacanciesAndResumes.Exceptions.Resume;

public class MiddleNameWhitespaceException extends RuntimeException {
    public MiddleNameWhitespaceException() {
        super("Неверно заполнено поле");
    }
}
