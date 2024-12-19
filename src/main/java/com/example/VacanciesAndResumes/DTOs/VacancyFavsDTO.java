package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyFavsDTO {
    @JsonProperty(value = "employee_id")
    private String employeeId;
    @JsonProperty(value = "vacancy_id")
    private String vacancyId;
}
