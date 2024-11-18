package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentVacancyPostDTO {
    @JsonProperty(value = "employee_id")
    private String employeeId;
    @JsonProperty(value = "comment_text")
    private String commentText;
    @JsonProperty(value = "is_system_record")
    private boolean isSystemRecord;
}
