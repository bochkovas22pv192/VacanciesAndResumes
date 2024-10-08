package com.example.VacanciesAndResumes.Exceptions.Resume;

public class FirstNameWhitespaceException extends RuntimeException {
    public FirstNameWhitespaceException() {
        super("Неверно заполнено поле");
    }
}
