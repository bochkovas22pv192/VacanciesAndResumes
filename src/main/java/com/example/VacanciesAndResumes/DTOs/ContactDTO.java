package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDTO {
    @JsonProperty(value = "mobile_phone")
    private  String mobilePhone;
    private  String email;
    private  String telegram;
    private  String whatsapp;
    private  String vk;
    private  String habr;
    private  String linkedin;
    private  String github;
}
