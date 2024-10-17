package com.example.VacanciesAndResumes.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkExperienceDTO {
    @JsonProperty(value = "organization_name")
    private String organizationName;
    private String industry;
    private String website;
    private String city;
    @JsonProperty(value = "role_name")
    private String roleName;
    @JsonProperty(value = "start_data")
    private String startDate;
    @JsonProperty(value = "is_current_job")
    private boolean isCurrentJob;
    @JsonProperty(value = "end_data")
    private String endDate;
    @JsonProperty(value = "additional_info")
    private String additionalInfo;
}
