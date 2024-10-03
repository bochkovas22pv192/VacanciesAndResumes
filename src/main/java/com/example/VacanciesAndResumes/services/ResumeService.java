package com.example.VacanciesAndResumes.services;

import com.example.VacanciesAndResumes.DTOs.ResumeDTO;
import com.example.VacanciesAndResumes.DTOs.ResumePostAnswerDTO;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.models.Document;
import com.example.VacanciesAndResumes.models.Language;
import com.example.VacanciesAndResumes.models.Resume;
import com.example.VacanciesAndResumes.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {

    AdditionalInfoRepository additionalInfoRepository;
    CertificatesQualificationRepository certificatesQualificationRepository;
    ContactRepository contactRepository;
    DocumentRepository documentRepository;
    EducationRepository educationRepository;
    LanguageRepository languageRepository;
    PersonalInfoRepository personalInfoRepository;
    SpecializationRepository specializationRepository;
    WorkExperienceRepository workExperienceRepository;

    ResumeMapper resumeMapper;

    public List<ResumeDTO> getResumeAll(){
        List<ResumeDTO> result = new java.util.ArrayList<>(List.of());
        List<Resume> temp = new java.util.ArrayList<>(List.of());

        return result;
    }

    public ResumePostAnswerDTO createResume(ResumeDTO resumeDTO){
        Resume resume = resumeMapper.resumeDTOToResume(resumeDTO);

        resume.getAdditionalInfo().setPersonalInfo(resume.getPersonalInfo());
        resume.getContact().setPersonalInfo(resume.getPersonalInfo());
        resume.getEducation().setPersonalInfo(resume.getPersonalInfo());
        resume.getSpecialization().setPersonalInfo(resume.getPersonalInfo());
        resume.getCertificatesQualification().setPersonalInfo(resume.getPersonalInfo());
        resume.getWorkExperience().setPersonalInfo(resume.getPersonalInfo());

        for(Language language : resume.getLanguages()){
            language.setPersonalInfo(resume.getPersonalInfo());
            languageRepository.save(language);
        }
        for (Document document : resume.getDocuments()){
            document.setPersonalInfo(resume.getPersonalInfo());
            documentRepository.save(document);
        }

        additionalInfoRepository.save(resume.getAdditionalInfo());
        contactRepository.save(resume.getContact());
        educationRepository.save(resume.getEducation());
        specializationRepository.save(resume.getSpecialization());
        certificatesQualificationRepository.save(resume.getCertificatesQualification());
        workExperienceRepository.save(resume.getWorkExperience());

        return new ResumePostAnswerDTO("success", "Успешно сохранено");
    }
}
