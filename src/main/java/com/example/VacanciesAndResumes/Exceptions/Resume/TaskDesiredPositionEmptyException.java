package com.example.VacanciesAndResumes.Exceptions.Resume;

public class TaskDesiredPositionEmptyException extends RuntimeException {
    public TaskDesiredPositionEmptyException() {
        super("Неверно заполнено поле");
    }
}