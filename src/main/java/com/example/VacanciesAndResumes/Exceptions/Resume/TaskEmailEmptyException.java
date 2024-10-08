package com.example.VacanciesAndResumes.Exceptions.Resume;

public class TaskEmailEmptyException extends RuntimeException {
    public TaskEmailEmptyException() {
        super("Неверно заполнено поле");
    }
}
