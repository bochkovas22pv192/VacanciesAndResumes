package com.example.VacanciesAndResumes.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResumeGetStatusAnswerDTO {
   private String status;
   private List<ResumeStatusDTO> result;
}
