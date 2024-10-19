package com.example.VacanciesAndResumes.services;


import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.VacancyDTO;
import com.example.VacanciesAndResumes.exceptions.vacancies.SalaryFormatException;
import com.example.VacanciesAndResumes.exceptions.vacancies.TaskGradeEmptyException;
import com.example.VacanciesAndResumes.exceptions.vacancies.TaskRoleEmptyException;
import com.example.VacanciesAndResumes.mappers.VacancyMapper;
import com.example.VacanciesAndResumes.models.Vacancy;
import com.example.VacanciesAndResumes.repositories.VacancyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    private final VacancyMapper vacancyMapper;

    public List<VacancyDTO> getVacancyAll(){

        return vacancyMapper.vacancyToVacancyDTO(vacancyRepository.findAll());
    }

    public ResumeAnswerDTO createVacancy(VacancyDTO vacancyDTO){
//        if(vacancyDTO.getGrade().isEmpty()){
//            throw  new TaskGradeEmptyException();
//        }
//        if(vacancyDTO.getRole().isEmpty()){
//            throw  new TaskRoleEmptyException();
//        }
//        if(vacancyDTO.getSalary().matches(".*[a-zA-Z]+.*")){
//            throw  new SalaryFormatException();
//        }
//        Vacancy vacancy = vacancyMapper.vacancyDTOToVacancy(vacancyDTO);
//
//        vacancyRepository.save(vacancy);

        return new ResumeAnswerDTO("success", "Успешно сохранено");
    }
}
