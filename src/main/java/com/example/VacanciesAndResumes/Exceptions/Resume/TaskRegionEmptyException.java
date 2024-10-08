package com.example.VacanciesAndResumes.Exceptions.Resume;

public class TaskRegionEmptyException extends RuntimeException {
    public TaskRegionEmptyException() {
        super("Неверно заполнено поле");
    }
}
