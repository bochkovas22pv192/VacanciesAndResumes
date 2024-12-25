package com.example.VacanciesAndResumes.services;


import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.DTOs.patch.CommentVacancyPatchDTO;
import com.example.VacanciesAndResumes.DTOs.patch.VacancyPatchDTO;
import com.example.VacanciesAndResumes.exceptions.resume.BadRequestException;
import com.example.VacanciesAndResumes.mappers.ResumeMapper;
import com.example.VacanciesAndResumes.mappers.VacancyMapper;
import com.example.VacanciesAndResumes.models.*;
import com.example.VacanciesAndResumes.repositories.*;
import com.example.VacanciesAndResumes.specifications.SpecificationVacancy;
import com.example.VacanciesAndResumes.specifications.SpecificationVacancySearch;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Transactional
@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final CustomerRepository customerRepository;
    private final CommentVacancyRepository commentVacancyRepository;
    private final EmployeeRepository employeeRepository;
    private final VacancyMapper vacancyMapper;
    private final SpecificationVacancy specificationVacancy;
    private final SpecificationVacancySearch specificationVacancySearch;
    private final FavoriteVacancyRepository favoriteVacancyRepository;

    private final HashSet<String> sortValues = new HashSet<String>(List.of("title", "roleName", "salary", "country", "region", "city", "createdAt"));

    @Autowired
    ObjectMapper objectMapper;

    private final ResumeMapper resumeMapper;
    private final HandbookRepository handbookRepository;


    public VacancyGetAnswerDTO getVacancyAll(Map<String, String> queryParams){
        VacancyQueryParamDTO vacancyQueryParamDTO = vacancyMapper.queryMapToVacancyQueryParamDTO(queryParams);

        Specification<Vacancy> spec = specificationVacancy.build(vacancyQueryParamDTO);

        Sort sort = Sort.by("title");
        if (queryParams.containsKey("sort")){
            if (!sortValues.contains(queryParams.get("sort"))){
                throw new BadRequestException("Нет поля с таким именем для сортировки");
            }
            sort = Sort.by(queryParams.get("sort"));
        }

        int page = queryParams.get("page") == null ? 1 : Integer.parseInt(queryParams.get("page"));

        List<Vacancy> vacancies = vacancyRepository.findAll(spec, PageRequest.of(page - 1, 10, sort)).getContent();

        return new VacancyGetAnswerDTO ("ok", vacancyMapper.vacancyToVacancyGetDTO(vacancies, UUID.fromString(vacancyQueryParamDTO.getAuthor())), page);
    }

    public VacancyGetAnswerDTO searchVacancy(String q, UUID ownerId, int page, int pageSize){
        Specification<Vacancy> spec = specificationVacancySearch.buildSpecification(q);
        List<Vacancy> vacancies = vacancyRepository.findAll(spec, PageRequest.of(page - 1, pageSize, Sort.by("title"))).getContent();
        return new VacancyGetAnswerDTO ("ok", vacancyMapper.vacancyToVacancyGetDTO(vacancies, ownerId), page);
    }

    public ResumeAnswerDTO createVacancy(VacancyDTO vacancyDTO){
        if(vacancyDTO.getGrade().isEmpty()){
            throw new BadRequestException("Введено недопустимое значения поля «Грэйд»");
        }
        if(vacancyDTO.getRoleName().isEmpty()){
            throw new BadRequestException("Введено недопустимое значения поля «Роль»");
        }


        Vacancy vacancy = vacancyMapper.vacancyDTOToVacancy(vacancyDTO);

        vacancy.setEmployee(employeeRepository.findById(UUID.fromString(vacancyDTO.getOwnerId()))
                .orElseThrow(() -> new BadRequestException("Нет такого работника")));
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

    public CommentVacancyGetDTO getCommentsForVacancy(UUID vacancyId){

        List<CommentVacancy> temp = commentVacancyRepository
                .findByVacancy(vacancyRepository.findById(vacancyId).orElseThrow(()->new BadRequestException("Нет вакансии с таким id")));

        List<CommentVacancyDTO> result = vacancyMapper.commentVacancyToCommentVacancyDTO(temp);

        return new CommentVacancyGetDTO("success", "Данные об истории взаимодействий получены.", result);
    }

    public CommentVacancyPostAnswerDTO createCommentVacancy(UUID vacancyId, CommentVacancyPostDTO commentVacancyPostDTO){

        CommentVacancy commentVacancy = vacancyMapper.commentVacancyPostDTOToCommentVacancy(commentVacancyPostDTO);

        commentVacancy.setVacancy(vacancyRepository.findById(vacancyId).orElseThrow(() -> new BadRequestException("Нет вакансии с таким id")));
        commentVacancy.setEmployee(employeeRepository.findById(UUID.fromString(commentVacancyPostDTO.getEmployeeId()))
                .orElseThrow(() -> new BadRequestException("Нет работника с таким id")));
        commentVacancy.setCreatedAt(LocalDateTime.now());

        commentVacancyRepository.save(commentVacancy);
        return new CommentVacancyPostAnswerDTO("success", "OK", commentVacancyRepository.findAll().getLast().getId());
    }

    public CommentVacancyPostAnswerDTO updateCommentVacancy (UUID id, JsonPatch jsonPatch)
            throws JsonPatchException, IOException {
        CommentVacancy commentVacancy =
                commentVacancyRepository.findById(id).orElseThrow(() -> new BadRequestException("Нет комментария с таким id"));
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(vacancyMapper.commentVacancyToCommentVacancyPatchDTO(commentVacancy),
                JsonNode.class));
        CommentVacancyPatchDTO newCommentVacancy = objectMapper.treeToValue(patched, CommentVacancyPatchDTO.class);

        commentVacancy.setCommentText(newCommentVacancy.getCommentText());
        commentVacancy.setSystemRecord(newCommentVacancy.isSystemRecord());
        
        commentVacancyRepository.save(commentVacancy);

        return new CommentVacancyPostAnswerDTO("Комментарий успешно обновлен", "OK",
                id);
    }

    public ResumeAnswerDTO deleteCommentVacancy (UUID id){
        commentVacancyRepository.deleteById(id);
        return new ResumeAnswerDTO("OK", "Комментарий успешно удален.");
    }
}
