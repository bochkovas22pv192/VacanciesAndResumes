package com.example.VacanciesAndResumes.Exceptions.Vacancies;

public class SalaryFormatException extends RuntimeException {
    public SalaryFormatException() {
        super("Введено недопустимое значения поля «Зарплата»");
    }
}
