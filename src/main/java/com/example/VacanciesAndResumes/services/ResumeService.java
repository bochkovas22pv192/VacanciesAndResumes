package com.example.VacanciesAndResumes.services;

import com.example.VacanciesAndResumes.DTOs.ResumeDTO;
import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.WorkExperienceDTO;
import com.example.VacanciesAndResumes.exceptions.resume.*;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.models.*;
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


    private  final CertificatesQualificationRepository certificatesQualificationRepository;
    private  final ContactRepository contactRepository;
    private  final DocumentRepository documentRepository;
    private  final EducationRepository educationRepository;
    private  final LanguageRepository languageRepository;
    private  final CandidateRepository candidateRepository;
    private  final SpecializationRepository specializationRepository;
    private  final WorkExperienceRepository workExperienceRepository;

    @Autowired
    ResumeMapper resumeMapper;

    private boolean checkForLetters(String s){
        return s.matches(".*[a-zA-Z]+.*");
    }

    public List<ResumeDTO> getResumeAll(){
        List<Resume> resumes = new java.util.ArrayList<>(List.of());
        List<Candidate> candidates = candidateRepository.findAll();
        for (int i = 0; i < candidates.size(); i++) {
            Resume creatingResume = new Resume();
            creatingResume.setCandidate(candidates.get(i));
            creatingResume.setCertificatesQualifications(List.of(certificatesQualificationRepository.findAll().get(i)));
            creatingResume.setContact(contactRepository.findAll().get(i));
            creatingResume.setEducations(List.of(educationRepository.findAll().get(i)));
            creatingResume.setSpecialization(specializationRepository.findAll().get(i));
            creatingResume.setWorkExperiences(List.of(workExperienceRepository.findAll().get(i)));
            creatingResume.setLanguages((List<Language>) candidates.get(i).getLanguages());
            creatingResume.setDocuments((List<Document>) candidates.get(i).getDocuments());
            resumes.add(creatingResume);
        }

        return resumeMapper.resumeToResumeDTO(resumes);
    }

    public ResumeAnswerDTO createResume(ResumeDTO resumeDTO){
        if(resumeDTO.getCandidate().getLastName().isBlank()){
            throw new BadRequestException("Неверно заполнено поле \"Фамилия\"");
        }
        if(resumeDTO.getCandidate().getFirstName().isBlank()){
            throw new BadRequestException("Неверно заполнено поле \"Имя\"");
        }
        if(resumeDTO.getCandidate().getMiddleName().isBlank()){
            throw new BadRequestException("Неверно заполнено поле \"Отчество\"");
        }
        if(resumeDTO.getCandidate().getCountry().isEmpty()){
            throw new BadRequestException("Неверно заполнено поле \"Страна\"");
        }
        if(resumeDTO.getCandidate().getRegion().isEmpty()){
            throw new BadRequestException("Неверно заполнено поле \"Регион\"");
        }
        if(resumeDTO.getCandidate().getCity().isEmpty()){
            throw new BadRequestException("Неверно заполнено поле \"Город\"");
        }

        if(resumeDTO.getContact().getMobilePhone().isBlank()){
            throw new BadRequestException("Неверно заполнено поле \"Мобильный телефон\"");
        }
        if(resumeDTO.getContact().getEmail().isEmpty()){
            throw new BadRequestException("Неверно заполнено поле \"Электронная почта\"");
        }
        if(resumeDTO.getContact().getTelegram().isEmpty()){
            throw new BadRequestException("Неверно заполнено поле \"Телеграм\"");
        }
        if(checkForLetters(resumeDTO.getContact().getWhatsapp())){
            throw new BadRequestException("Неверно заполнено поле \"Ватсап\"");
        }
        if(resumeDTO.getSpecialization().getRoleName().isEmpty()){
            throw new BadRequestException("Неверно заполнено поле \"Желаемая позиция\"");
        }
        for(WorkExperienceDTO workExperienceDTO : resumeDTO.getWorkExperiences()){
            if(checkForLetters(workExperienceDTO.getStartDate())){
                throw new BadRequestException("Неверно заполнено поле \"Дата начала\"");
            }
            if(checkForLetters(workExperienceDTO.getEndDate())){
                throw new BadRequestException("Неверно заполнено поле \"Дата окончания\"");
            }
        }


        Resume resume = resumeMapper.resumeDTOToResume(resumeDTO);

        if (resume.getCandidate().getBirthDate().isAfter(LocalDate.now())){
            throw new BadRequestException("Неверно заполнено поле \"Дата рождения\"");
        }

        resume.getContact().setCandidate(resume.getCandidate());
        resume.getSpecialization().setCandidate(resume.getCandidate());

        for(Education education : resume.getEducations()){
            education.setCandidate(resume.getCandidate());
        }
        for(CertificatesQualification certificatesQualification : resume.getCertificatesQualifications()){
            certificatesQualification.setCandidate(resume.getCandidate());
        }
        for(WorkExperience workExperience : resume.getWorkExperiences()){
            workExperience.setCandidate(resume.getCandidate());
        }

        for(Language language : resume.getLanguages()){
            language.setCandidate(resume.getCandidate());
            languageRepository.save(language);
        }
        for (Document document : resume.getDocuments()){
            document.setCandidate(resume.getCandidate());
            documentRepository.save(document);
        }

        candidateRepository.save(resume.getCandidate());
        contactRepository.save(resume.getContact());
        educationRepository.saveAll(resume.getEducations());
        specializationRepository.save(resume.getSpecialization());
        certificatesQualificationRepository.saveAll(resume.getCertificatesQualifications());
        workExperienceRepository.saveAll(resume.getWorkExperiences());

        return new ResumeAnswerDTO("success", "Успешно сохранено");
    }
}
