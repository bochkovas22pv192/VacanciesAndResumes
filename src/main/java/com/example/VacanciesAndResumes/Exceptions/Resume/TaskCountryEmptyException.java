package com.example.VacanciesAndResumes.Exceptions.Resume;

public class TaskCountryEmptyException extends RuntimeException {
    public TaskCountryEmptyException() {
        super("Неверно заполнено поле");
    }
}
