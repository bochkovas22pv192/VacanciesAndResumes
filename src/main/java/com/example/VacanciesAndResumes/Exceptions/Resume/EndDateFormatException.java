package com.example.VacanciesAndResumes.Exceptions.Resume;

public class EndDateFormatException extends RuntimeException {
    public EndDateFormatException() {
        super("Неверно заполнено поле");
    }
}
