package com.example.VacanciesAndResumes.Exceptions.Resume;


public class LastNameWhitespaceException extends RuntimeException {
    public LastNameWhitespaceException() {
        super("Неверно заполнено поле");
    }
}
