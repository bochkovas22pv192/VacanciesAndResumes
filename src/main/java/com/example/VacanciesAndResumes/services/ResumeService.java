package com.example.VacanciesAndResumes.services;

import com.example.VacanciesAndResumes.DTOs.ResumeDTO;
import com.example.VacanciesAndResumes.DTOs.ResumePostAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.WorkExperienceDTO;
import com.example.VacanciesAndResumes.Exceptions.Resume.*;
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

    private  final AdditionalInfoRepository additionalInfoRepository;
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
            creatingResume.setAdditionalInfo(additionalInfoRepository.findAll().get(i));
            creatingResume.setCertificatesQualification(List.of(certificatesQualificationRepository.findAll().get(i)));
            creatingResume.setContact(contactRepository.findAll().get(i));
            creatingResume.setEducation(List.of(educationRepository.findAll().get(i)));
            creatingResume.setSpecialization(specializationRepository.findAll().get(i));
            creatingResume.setWorkExperience(List.of(workExperienceRepository.findAll().get(i)));
            creatingResume.setLanguages((List<Language>) candidates.get(i).getLanguages());
            creatingResume.setDocuments((List<Document>) candidates.get(i).getDocuments());
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
        if(resumeDTO.getPersonalInfo().getCountry().isEmpty()){
            throw new TaskCountryEmptyException();
        }
        if(resumeDTO.getPersonalInfo().getRegion().isEmpty()){
            throw new TaskRegionEmptyException();
        }
        if(resumeDTO.getPersonalInfo().getCity().isEmpty()){
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
        for(WorkExperienceDTO workExperienceDTO : resumeDTO.getWorkExperience()){
            if(checkForLetters(workExperienceDTO.getStartDate())){
                throw new StartDateFormatException();
            }
            if(checkForLetters(workExperienceDTO.getEndDate())){
                throw new EndDateFormatException();
            }
        }


        Resume resume = resumeMapper.resumeDTOToResume(resumeDTO);

        if (resume.getCandidate().getBirthDate().isAfter(LocalDate.now())){
            throw new DateOfBirthMinDateException();
        }

        resume.getAdditionalInfo().setCandidate(resume.getCandidate());
        resume.getContact().setCandidate(resume.getCandidate());
        resume.getSpecialization().setCandidate(resume.getCandidate());

        for(Education education : resume.getEducation()){
            education.setCandidate(resume.getCandidate());
        }
        for(CertificatesQualification certificatesQualification : resume.getCertificatesQualification()){
            certificatesQualification.setCandidate(resume.getCandidate());
        }
        for(WorkExperience workExperience : resume.getWorkExperience()){
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
        additionalInfoRepository.save(resume.getAdditionalInfo());
        contactRepository.save(resume.getContact());
        educationRepository.saveAll(resume.getEducation());
        specializationRepository.save(resume.getSpecialization());
        certificatesQualificationRepository.saveAll(resume.getCertificatesQualification());
        workExperienceRepository.saveAll(resume.getWorkExperience());

        return new ResumePostAnswerDTO("success", "Успешно сохранено");
    }
}
