package com.example.VacanciesAndResumes.services;

import com.example.VacanciesAndResumes.DTOs.ResumeAnswerDTO;
import com.example.VacanciesAndResumes.DTOs.VacancyFavsDTO;
import com.example.VacanciesAndResumes.exceptions.resume.BadRequestException;
import com.example.VacanciesAndResumes.models.Employee;
import com.example.VacanciesAndResumes.models.FavoriteVacancy;
import com.example.VacanciesAndResumes.models.Vacancy;
import com.example.VacanciesAndResumes.repositories.EmployeeRepository;
import com.example.VacanciesAndResumes.repositories.FavoriteVacancyRepository;
import com.example.VacanciesAndResumes.repositories.VacancyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteVacancyService {

    private final VacancyRepository vacancyRepository;
    private final EmployeeRepository employeeRepository;
    private final FavoriteVacancyRepository favoriteVacancyRepository;

    public VacancyFavsDTO addFavoriteVacancy(VacancyFavsDTO vacancyFavsDTO){
        FavoriteVacancy favoriteVacancy = new FavoriteVacancy();
        favoriteVacancy.setVacancy(vacancyRepository.findById(UUID.fromString(vacancyFavsDTO.getVacancyId()))
                .orElseThrow(()-> new BadRequestException("Нет вакансии с таким id")));
        favoriteVacancy.setEmployee(employeeRepository.findById(UUID.fromString(vacancyFavsDTO.getEmployeeId()))
                .orElseThrow(() -> new BadRequestException("Нет работника с таким id")));
        favoriteVacancyRepository.save(favoriteVacancy);
        return vacancyFavsDTO;
    }

    public ResumeAnswerDTO deleteFavoriteVacancy(VacancyFavsDTO vacancyFavsDTO){
        Vacancy vacancy = vacancyRepository.findById(UUID.fromString(vacancyFavsDTO.getVacancyId()))
                .orElseThrow(()-> new BadRequestException("Нет вакансии с таким id"));
        Employee employee = employeeRepository.findById(UUID.fromString(vacancyFavsDTO.getEmployeeId()))
                .orElseThrow(() -> new BadRequestException("Нет работника с таким id"));
        FavoriteVacancy favoriteVacancy = favoriteVacancyRepository.findByVacancyAndEmployee(vacancy, employee);
        favoriteVacancyRepository.delete(favoriteVacancy);
        return new ResumeAnswerDTO("success", "Успешно удалено из избранного");
    }

}
