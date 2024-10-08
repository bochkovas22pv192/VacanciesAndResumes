package com.example.VacanciesAndResumes.Exceptions.Vacancies;

public class TaskRoleEmptyException extends RuntimeException {
    public TaskRoleEmptyException() {
        super("Введено недопустимое значения поля «Роль»");
    }
}
