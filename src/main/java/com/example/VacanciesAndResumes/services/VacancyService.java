package com.example.VacanciesAndResumes.services;


import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.DTOs.patch.CandidatePatchDTO;
import com.example.VacanciesAndResumes.DTOs.patch.VacancyPatchDTO;
import com.example.VacanciesAndResumes.exceptions.resume.BadRequestException;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.mappers.VacancyMapper;
import com.example.VacanciesAndResumes.models.Candidate;
import com.example.VacanciesAndResumes.models.CommentVacancy;
import com.example.VacanciesAndResumes.models.Customer;
import com.example.VacanciesAndResumes.models.Vacancy;
import com.example.VacanciesAndResumes.repositories.CommentVacancyRepository;
import com.example.VacanciesAndResumes.repositories.CustomerRepository;
import com.example.VacanciesAndResumes.repositories.HandbookRepository;
import com.example.VacanciesAndResumes.repositories.VacancyRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final CustomerRepository customerRepository;
    private final CommentVacancyRepository commentVacancyRepository;

    private final VacancyMapper vacancyMapper;

    @Autowired
    ObjectMapper objectMapper;

    private final ResumeMapper resumeMapper;
    private final HandbookRepository handbookRepository;

    public List<VacancyDTO> getVacancyAll(){

        return vacancyMapper.vacancyToVacancyDTO(vacancyRepository.findAll());
    }

    public ResumeAnswerDTO createVacancy(VacancyDTO vacancyDTO){
        if(vacancyDTO.getGrade().isEmpty()){
            throw new BadRequestException("Введено недопустимое значения поля «Грэйд»");
        }
        if(vacancyDTO.getRoleName().isEmpty()){
            throw new BadRequestException("Введено недопустимое значения поля «Роль»");
        }
        Vacancy vacancy = vacancyMapper.vacancyDTOToVacancy(vacancyDTO);
        vacancy.setCreatedAt(LocalDateTime.now());
        vacancy.setActive(true);
        Customer customer = customerRepository.findByName(vacancy.getCustomer().getName());
        if(customer == null){
            throw new BadRequestException("Не существует организации " + vacancy.getCustomer().getName());
        }
        vacancy.setCustomer(customer);
        vacancyRepository.save(vacancy);

        return new ResumeAnswerDTO("success", "Успешно сохранено");
    }

    public ResumeGetStatusAnswerDTO getVacancyStatuses (){
        ResumeGetStatusAnswerDTO result = new ResumeGetStatusAnswerDTO();
        result.setResult(resumeMapper.HandbookToResumeStatusDTO(handbookRepository.findByCode("Vacancy Status")));
        result.setStatus("success");
        return result;
    }

    public ResumeAnswerDTO updateVacancyPatch(UUID id, JsonPatch jsonPatch) throws JsonPatchException, IOException {
        Vacancy vacancy = vacancyRepository.findById(id).orElseThrow(() -> new BadRequestException("Нет вакансии с таким id"));
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(vacancyMapper.vacancyToVacancyPatchDTO(vacancy), JsonNode.class));
        Vacancy newVacancy = vacancyMapper.vacancyPatchDTOToVacancy(objectMapper.treeToValue(patched, VacancyPatchDTO.class));
        vacancy.setTitle(newVacancy.getTitle());
        vacancy.setRoleName(newVacancy.getRoleName());
        vacancy.setDescription(newVacancy.getDescription());
        vacancy.setSalary(newVacancy.getSalary());
        vacancy.setCurrency(newVacancy.getCurrency());
        vacancy.setGrade(newVacancy.getGrade());
        vacancy.setCountry(newVacancy.getCountry());
        vacancy.setRegion(newVacancy.getRegion());
        vacancy.setCity(newVacancy.getCity());
        vacancy.setActive(newVacancy.isActive());
        vacancyRepository.save(vacancy);
        return new ResumeAnswerDTO("success", "Успешно изменено");
    }

    public CommentVacancyPostAnswerDTO createCommentVacancy(UUID vacancyId, CommentVacancyPostDTO commentVacancyPostDTO){

        CommentVacancy commentVacancy = vacancyMapper.commentVacancyPostDTOToCommentVacancy(commentVacancyPostDTO);

        commentVacancy.setVacancy(vacancyRepository.findById(vacancyId).orElseThrow(() -> new BadRequestException("Нет вакансии с таким id")));
        commentVacancy.setCreatedAt(LocalDateTime.now());

        commentVacancyRepository.save(commentVacancy);
        return new CommentVacancyPostAnswerDTO("success", "OK", commentVacancyRepository.findAll().getLast().getId());
    }
}
