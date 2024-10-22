package com.example.VacanciesAndResumes.mappers;

import com.example.VacanciesAndResumes.DTOs.CustomerDTO;
import com.example.VacanciesAndResumes.DTOs.VacancyDTO;
import com.example.VacanciesAndResumes.models.Customer;
import com.example.VacanciesAndResumes.models.Vacancy;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface VacancyMapper {
    VacancyDTO vacancyToVacancyDTO(Vacancy entity);
    Vacancy vacancyDTOToVacancy(VacancyDTO entity);

    List<VacancyDTO> vacancyToVacancyDTO(List<Vacancy> entity);
    List<Vacancy> vacancyDTOToVacancy(List<VacancyDTO> entity);

    CustomerDTO customerToCustomerDTO(Customer entity);
    Customer customerDTOToCustomer(CustomerDTO entity);
}
