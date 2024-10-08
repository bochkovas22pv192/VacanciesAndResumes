package com.example.VacanciesAndResumes.Exceptions.Resume;

public class StartDateFormatException extends RuntimeException {
    public StartDateFormatException() {
        super("Неверно заполнено поле");
    }
}
