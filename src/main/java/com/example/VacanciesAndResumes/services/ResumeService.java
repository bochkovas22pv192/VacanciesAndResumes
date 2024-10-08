package com.example.VacanciesAndResumes.services;

import com.example.VacanciesAndResumes.DTOs.ResumeDTO;
import com.example.VacanciesAndResumes.DTOs.ResumePostAnswerDTO;
import com.example.VacanciesAndResumes.Exceptions.Resume.*;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.models.Document;
import com.example.VacanciesAndResumes.models.Language;
import com.example.VacanciesAndResumes.models.PersonalInfo;
import com.example.VacanciesAndResumes.models.Resume;
import com.example.VacanciesAndResumes.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    private boolean checkForLetters(String s){
        return s.matches(".*[a-zA-Z]+.*");
    }

    public List<ResumeDTO> getResumeAll(){
        List<Resume> resumes = new java.util.ArrayList<>(List.of());
        List<PersonalInfo> personalInfos = personalInfoRepository.findAll();
        for (int i = 0; i < personalInfos.size(); i++) {
            Resume creatingResume = new Resume();
            creatingResume.setPersonalInfo(personalInfos.get(i));
            creatingResume.setAdditionalInfo(additionalInfoRepository.findAll().get(i));
            creatingResume.setCertificatesQualification(certificatesQualificationRepository.findAll().get(i));
            creatingResume.setContact(contactRepository.findAll().get(i));
            creatingResume.setEducation(educationRepository.findAll().get(i));
            creatingResume.setSpecialization(specializationRepository.findAll().get(i));
            creatingResume.setWorkExperience(workExperienceRepository.findAll().get(i));
            creatingResume.setLanguages((List<Language>) personalInfos.get(i).getLanguages());
            creatingResume.setDocuments((List<Document>) personalInfos.get(i).getDocuments());
            resumes.add(creatingResume);
        }

        return resumeMapper.resumeToResumeDTO(resumes);
    }

    public ResumePostAnswerDTO createResume(ResumeDTO resumeDTO){
        if(resumeDTO.getPersonalInfo().getLastName().isBlank()){
            throw new LastNameWhitespaceException();
        }
        if(resumeDTO.getPersonalInfo().getFirstName().isBlank()){
            throw new FirstNameWhitespaceException();
        }
        if(resumeDTO.getPersonalInfo().getMiddleName().isBlank()){
            throw new MiddleNameWhitespaceException();
        }
        if(resumeDTO.getPersonalInfo().getCountryName().isEmpty()){
            throw new TaskCountryEmptyException();
        }
        if(resumeDTO.getPersonalInfo().getRegionName().isEmpty()){
            throw new TaskRegionEmptyException();
        }
        if(resumeDTO.getPersonalInfo().getCityName().isEmpty()){
            throw new TaskCityEmptyException();
        }

        if(resumeDTO.getContact().getMobilePhone().isBlank()){
            throw new MobilePhoneWhitespaceException();
        }
        if(resumeDTO.getContact().getEmail().isEmpty()){
            throw new TaskEmailEmptyException();
        }
        if(resumeDTO.getContact().getTelegram().isEmpty()){
            throw new TaskTelegramEmptyException();
        }
        if(checkForLetters(resumeDTO.getContact().getWhatsapp())){
            throw new WhatsAppFormatException();
        }
        if(resumeDTO.getSpecialization().getDesiredPosition().isEmpty()){
            throw new TaskDesiredPositionEmptyException();
        }
        if(checkForLetters(resumeDTO.getWorkExperience().getStartDate())){
            throw new StartDateFormatException();
        }
        if(checkForLetters(resumeDTO.getWorkExperience().getEndDate())){
            throw new EndDateFormatException();
        }


        Resume resume = resumeMapper.resumeDTOToResume(resumeDTO);

        if (resume.getPersonalInfo().getDateOfBirth().isAfter(LocalDate.now())){
            throw new DateOfBirthMinDateException();
        }



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
