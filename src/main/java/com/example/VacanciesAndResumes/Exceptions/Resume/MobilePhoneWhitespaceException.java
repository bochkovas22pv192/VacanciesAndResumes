package com.example.VacanciesAndResumes.Exceptions.Resume;

public class MobilePhoneWhitespaceException extends RuntimeException {
    public MobilePhoneWhitespaceException() {
        super("Неверно заполнено поле");
    }
}
