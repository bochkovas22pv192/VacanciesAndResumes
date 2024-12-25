package com.example.VacanciesAndResumes.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyGetAnswerDTO {
    private String status;
    private List<VacancyGetDTO> result;
    private int page;
}
