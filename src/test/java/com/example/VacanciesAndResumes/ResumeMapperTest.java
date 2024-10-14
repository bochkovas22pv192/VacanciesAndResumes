package com.example.VacanciesAndResumes;

import com.example.VacanciesAndResumes.DTOs.AdditionalInfoDTO;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.models.AdditionalInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResumeMapperTest {

    @Autowired
    ResumeMapper resumeMapper;

    @Test
    void additionalInfoToAdditionalInfoDTO(){
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setEmploymentType("Полная занятость");
        additionalInfo.setWillingToTravel(true);
        additionalInfo.setWillingToRelocate(true);

        assertEquals(resumeMapper.additionalInfoToAdditionalInfoDTO(additionalInfo),
                new AdditionalInfoDTO(true, "Полная занятость", true));
        assertNotEquals(resumeMapper.additionalInfoToAdditionalInfoDTO(additionalInfo),
                new AdditionalInfoDTO(false, "Полная занятость", true));
        assertNotEquals(resumeMapper.additionalInfoToAdditionalInfoDTO(additionalInfo),
                new AdditionalInfoDTO(false, "Частичная занятость", true));
    }

    @Test
    void additionalInfoDTOToAdditionalInfo(){
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setEmploymentType("Полная занятость");
        additionalInfo.setWillingToTravel(true);
        additionalInfo.setWillingToRelocate(true);

        assertEquals(resumeMapper.additionalInfoDTOToAdditionalInfo(
                new AdditionalInfoDTO(true, "Полная занятость", true)),
                additionalInfo);

        assertNotEquals(resumeMapper.additionalInfoDTOToAdditionalInfo(
                new AdditionalInfoDTO(false, "Полная занятость", true)),
                additionalInfo);
        assertNotEquals(resumeMapper.additionalInfoDTOToAdditionalInfo(
                new AdditionalInfoDTO(true, "Частичная занятость", true)),
                additionalInfo);
    }
}
