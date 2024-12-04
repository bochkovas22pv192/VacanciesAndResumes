package com.example.VacanciesAndResumes.specifications;

import com.example.VacanciesAndResumes.DTOs.VacancyQueryParamDTO;
import com.example.VacanciesAndResumes.models.Employee;
import com.example.VacanciesAndResumes.models.Vacancy;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SpecificationVacancy {

    public Specification<Vacancy> build (VacancyQueryParamDTO vacancyQueryParamDTO){
        return withCountry(vacancyQueryParamDTO.getCountry())
                .and(withCity(vacancyQueryParamDTO.getCity()))
                .and(withMine(vacancyQueryParamDTO.isMine(), vacancyQueryParamDTO.getAuthor()));
    }

    private Specification<Vacancy> withCountry(String country){

        return (root, query, criteriaBuilder) -> {
            if (country == null || country.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("country"), country);
        };
    }

    private Specification<Vacancy> withCity(String city){

        return (root, query, criteriaBuilder) -> {
            if (city == null || city.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("city"), city);
        };
    }

    private Specification<Vacancy> withMine(boolean mine, String id){

        return (root, query, criteriaBuilder) -> {
            if (!mine || id == null){
                return criteriaBuilder.conjunction();
            }
            Join<Vacancy, Employee> vacancyOwner = root.join("employee");
            return criteriaBuilder.equal(vacancyOwner.get("id"), UUID.fromString(id));
        };
    }
}
