package com.example.VacanciesAndResumes.services;

import com.example.VacanciesAndResumes.DTOs.ResumeDTO;
import com.example.VacanciesAndResumes.DTOs.ResumePostAnswerDTO;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.models.Document;
import com.example.VacanciesAndResumes.models.Language;
import com.example.VacanciesAndResumes.models.Resume;
import com.example.VacanciesAndResumes.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ResumeService {

    private  final AdditionalInfoRepository additionalInfoRepository;
    private  final CertificatesQualificationRepository certificatesQualificationRepository;
    private  final ContactRepository contactRepository;
    private  final DocumentRepository documentRepository;
    private  final EducationRepository educationRepository;
    private  final LanguageRepository languageRepository;
    private  final PersonalInfoRepository personalInfoRepository;
    private  final SpecializationRepository specializationRepository;
    private  final WorkExperienceRepository workExperienceRepository;

    @Autowired
    ResumeMapper resumeMapper;

    public List<ResumeDTO> getResumeAll(){
        List<ResumeDTO> result = new java.util.ArrayList<>(List.of());
        List<Resume> temp = new java.util.ArrayList<>(List.of());

        return result;
    }

    public ResumePostAnswerDTO createResume(ResumeDTO resumeDTO){
        Resume resume = resumeMapper.resumeDTOToResume(resumeDTO);

        System.out.println(resume + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

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

        personalInfoRepository.save(resume.getPersonalInfo());
        additionalInfoRepository.save(resume.getAdditionalInfo());
        contactRepository.save(resume.getContact());
        educationRepository.save(resume.getEducation());
        specializationRepository.save(resume.getSpecialization());
        certificatesQualificationRepository.save(resume.getCertificatesQualification());
        workExperienceRepository.save(resume.getWorkExperience());

        return new ResumePostAnswerDTO("success", "Успешно сохранено");
    }
}
