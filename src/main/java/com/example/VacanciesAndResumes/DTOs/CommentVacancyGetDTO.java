package com.example.VacanciesAndResumes.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVacancyGetDTO {
    private String status;
    private String message;
    private List<CommentVacancyDTO> comments;
}
