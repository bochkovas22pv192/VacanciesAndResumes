package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentVacancyPostAnswerDTO {
    private String message;
    private String status;
    @JsonProperty(value = "comment_vacancy_id")
    private UUID commentVacancyId;
}
