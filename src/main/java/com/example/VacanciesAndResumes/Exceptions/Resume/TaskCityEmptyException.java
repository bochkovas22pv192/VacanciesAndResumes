package com.example.VacanciesAndResumes.Exceptions.Resume;

public class TaskCityEmptyException extends RuntimeException {
    public TaskCityEmptyException() {
        super("Неверно заполнено поле");
    }
}
