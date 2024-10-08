package com.example.VacanciesAndResumes.Exceptions.Resume;

public class TaskTelegramEmptyException extends RuntimeException {
    public TaskTelegramEmptyException() {
        super("Неверно заполнено поле");
    }
}
