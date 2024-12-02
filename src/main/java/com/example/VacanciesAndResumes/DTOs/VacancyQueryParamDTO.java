package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.BindParam;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyQueryParamDTO {
    private String country;
    private String region;
    private String city;
    private String role;
    private boolean status;
    private String grade;

    @JsonProperty(value = "salary_from")
    @BindParam(value = "salary_from")
    private int salaryFrom;

    @BindParam(value = "salary_to")
    @JsonProperty(value = "salary_to")
    private int salaryTo;

    private String skills;
    private boolean mine;
    private boolean favs;
    private int page;
    private boolean sort;
    private String author;
}
