package com.example.VacanciesAndResumes.services;


import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.DTOs.patch.CandidatePatchDTO;
import com.example.VacanciesAndResumes.exceptions.resume.*;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.models.*;
import com.example.VacanciesAndResumes.repositories.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;



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
    private final EmploymentRepository employmentRepository;
    private final KeySkillRepository keySkillRepository;
    private final HandbookRepository handbookRepository;

    @Autowired
    ResumeMapper resumeMapper;

    @Autowired
    ObjectMapper objectMapper;

    private boolean checkForLetters(String s){
        return s.matches(".*[a-zA-Z]+.*");
    }

    public Candidate getCandidateByID(UUID id){
        return candidateRepository.findById(id).orElseThrow(() -> new BadRequestException("Нет резюме с таким id"));
    }

    public List<ResumeDTO> getResumeAll(){
        List<Candidate> candidates = candidateRepository.findAll();

        return resumeMapper.candidateToResumeDTO(candidates);
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

        List<Employment> employmentList = new java.util.ArrayList<>(List.of());
        for(Employment employment : resume.getCandidate().getEmployments()){
            employmentList.add(employmentRepository.findByEmploymentName(employment.getEmploymentName()));
        }
        resume.getCandidate().getEmployments().clear();
        for (Employment employment : employmentList) {
            employment.getCandidates().add(resume.getCandidate());
        }

        List<KeySkill> keySkillList = new java.util.ArrayList<>(List.of());
        for(KeySkill keySkill : resume.getCandidate().getKeySkills()){
            keySkillList.add(keySkillRepository.findByKeySkillName(keySkill.getKeySkillName()));
        }
        resume.getCandidate().getKeySkills().clear();
        for (KeySkill keySkill : keySkillList) {
            keySkill.getCandidates().add(resume.getCandidate());
        }

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
        }
        for (Document document : resume.getDocuments()){
            document.setCandidate(resume.getCandidate());
        }

        resume.getSpecialization().setCandidate(resume.getCandidate());
        resume.getContact().setCandidate(resume.getCandidate());

        resume.getCandidate().setCreatedAt(LocalDateTime.now());
        resume.getCandidate().setStatus("Стакан резюме");

        candidateRepository.save(resume.getCandidate());
        contactRepository.save(resume.getContact());
        educationRepository.saveAll(resume.getEducations());
        specializationRepository.save(resume.getSpecialization());
        certificatesQualificationRepository.saveAll(resume.getCertificatesQualifications());
        documentRepository.saveAll(resume.getDocuments());
        languageRepository.saveAll(resume.getLanguages());
        workExperienceRepository.saveAll(resume.getWorkExperiences());

        return new ResumeAnswerDTO("success", "Успешно сохранено");
    }

    public ResumeGetStatusAnswerDTO getStatusList(){
        ResumeGetStatusAnswerDTO result = new ResumeGetStatusAnswerDTO();
        result.setResult(resumeMapper.HandbookToResumeStatusDTO(handbookRepository.findByCode("Resume Status")));
        result.setStatus("success");
        return result;
    }

    public ResumeAnswerDTO updateCandidatePatch(UUID id, JsonPatch jsonPatch) throws JsonPatchException, IOException {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new BadRequestException("Нет резюме с таким id"));
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(resumeMapper.candidateToCandidatePatchDTO(candidate), JsonNode.class));
        Candidate newCandidate = resumeMapper.candidatePatchDTOToCandidate(objectMapper.treeToValue(patched, CandidatePatchDTO.class));
        candidate.setLastName(newCandidate.getLastName());
        candidate.setFirstName(newCandidate.getFirstName());
        candidate.setMiddleName(newCandidate.getMiddleName());
        candidate.setGender(newCandidate.getGender());
        candidate.setBirthDate(newCandidate.getBirthDate());
        candidate.setCountry(newCandidate.getCountry());
        candidate.setRegion(newCandidate.getRegion());
        candidate.setCity(newCandidate.getCity());
        candidate.setCitizenship(newCandidate.getCitizenship());
        candidate.setStatus(newCandidate.getStatus());
        candidate.setHasWorkPermit(newCandidate.isHasWorkPermit());
        candidate.setRelocate(newCandidate.getRelocate());
        candidate.setTravel(newCandidate.getTravel());
        candidate.setCreatedAt(newCandidate.getCreatedAt());
        candidate.setDocOffer(newCandidate.isDocOffer());
        candidate.setDocScreen(newCandidate.isDocScreen());
        candidateRepository.save(candidate);
        return new ResumeAnswerDTO("success", "Успешно изменено");
    }
}
