package com.example.VacanciesAndResumes.Exceptions.Resume;

public class DateOfBirthMinDateException extends RuntimeException {
    public DateOfBirthMinDateException() {
        super("Неверно заполнено поле");
    }
}
