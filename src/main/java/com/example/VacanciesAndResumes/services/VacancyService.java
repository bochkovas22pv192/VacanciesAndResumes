package com.example.VacanciesAndResumes.services;


import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.VacancyDTO;
import com.example.VacanciesAndResumes.exceptions.resume.BadRequestException;
import com.example.VacanciesAndResumes.mappers.VacancyMapper;
import com.example.VacanciesAndResumes.models.Customer;
import com.example.VacanciesAndResumes.models.Vacancy;
import com.example.VacanciesAndResumes.repositories.CustomerRepository;
import com.example.VacanciesAndResumes.repositories.VacancyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final CustomerRepository customerRepository;

    private final VacancyMapper vacancyMapper;

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
            customer = customerRepository.save(vacancy.getCustomer());
        }
        vacancy.setCustomer(customer);
        vacancyRepository.save(vacancy);

        return new ResumeAnswerDTO("success", "Успешно сохранено");
    }
}
