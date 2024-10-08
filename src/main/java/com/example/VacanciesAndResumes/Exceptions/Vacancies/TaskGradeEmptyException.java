package com.example.VacanciesAndResumes.Exceptions.Vacancies;

public class TaskGradeEmptyException extends RuntimeException {
    public TaskGradeEmptyException() {
        super("Введено недопустимое значения поля «Грэйд»");
    }
}
