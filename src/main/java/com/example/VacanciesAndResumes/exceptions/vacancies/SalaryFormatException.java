package com.example.VacanciesAndResumes.exceptions.vacancies;

public class SalaryFormatException extends RuntimeException {
    public SalaryFormatException() {
        super("Введено недопустимое значения поля «Зарплата»");
    }
}
