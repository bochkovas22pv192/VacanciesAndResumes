package com.example.VacanciesAndResumes.exceptions.vacancies;

public class TaskRoleEmptyException extends RuntimeException {
    public TaskRoleEmptyException() {
        super("Введено недопустимое значения поля «Роль»");
    }
}
