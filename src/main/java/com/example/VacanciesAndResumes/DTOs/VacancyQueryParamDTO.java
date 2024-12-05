package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.BindParam;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyQueryParamDTO {
    private String country;
    private String region;
    private String city;
    private String role;
    private Boolean status;
    private List<String> grade;
    private Integer salaryFrom;
    private Integer salaryTo;
    private Boolean mine;
    private Boolean favs;
    private String author;
}
