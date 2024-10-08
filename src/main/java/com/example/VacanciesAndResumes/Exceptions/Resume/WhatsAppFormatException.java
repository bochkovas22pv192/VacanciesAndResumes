package com.example.VacanciesAndResumes.Exceptions.Resume;

public class WhatsAppFormatException extends RuntimeException {
    public WhatsAppFormatException() {
        super("Неверно заполнено поле");
    }
}
