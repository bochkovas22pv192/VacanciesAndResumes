package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacancyGetDTO {
    private String id;
    private String title;
    @JsonProperty(value = "role")
    private String roleName;
    private String grade;
    private Boolean status;
    @JsonProperty(value = "customer_name")
    private String customerName;
    @JsonProperty(value = "candidate_count")
    private int candidateCount;
    @JsonProperty(value = "in_fav")
    private Boolean inFav;
}
