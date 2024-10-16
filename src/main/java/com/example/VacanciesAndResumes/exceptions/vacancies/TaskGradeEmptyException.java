package com.example.VacanciesAndResumes.exceptions.vacancies;

public class TaskGradeEmptyException extends RuntimeException {
    public TaskGradeEmptyException() {
        super("Введено недопустимое значения поля «Грэйд»");
    }
}
