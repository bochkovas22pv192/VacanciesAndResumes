package com.example.VacanciesAndResumes.specifications;

import com.example.VacanciesAndResumes.DTOs.VacancyQueryParamDTO;
import com.example.VacanciesAndResumes.models.Customer;
import com.example.VacanciesAndResumes.models.Vacancy;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.reliab.tech.core.specification.helper.PrimitiveFilterHelper;

import java.util.UUID;


@Component
public class BaseFilterVacancy implements PrimitiveFilterHelper<Vacancy> {
    public Specification<Vacancy> byId(UUID id) {
        return fieldEqualsOrDefault("id", id, this::falseSpecification);
    }

    public Specification<Vacancy> buildSpecificationSearch (String q){
        if (q == null){
            return null;
        }
        return fieldContainsOrDefault("title", q, this::trueSpecification)
                .or(fieldContainsOrDefault("roleName", q, this::trueSpecification))
                .or(fieldContainsOrDefault("customer.name", q, this::trueSpecification));
    }

    private Specification<Vacancy> withTitle (String q){
        return  (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("title"), "%" + q + "%");
        };
    }

    private Specification<Vacancy> withRole (String q){
        return  (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("roleName"), "%" + q + "%");
        };
    }

    private Specification<Vacancy> withCustomerName (String q){
        return  (root, query, criteriaBuilder) -> {
            Join<Vacancy, Customer> vacancyCustomers = root.join("customer");
            return criteriaBuilder.like(vacancyCustomers.get("name"), "%" + q + "%");
        };
    }

    public Specification<Vacancy> build (VacancyQueryParamDTO vacancyQueryParamDTO){
        return fieldEqualsOrDefault("country", vacancyQueryParamDTO.getCountry(), this::trueSpecification)
                .and(fieldEqualsOrDefault("region", vacancyQueryParamDTO.getRegion(), this::trueSpecification))
                .and(fieldEqualsOrDefault("city", vacancyQueryParamDTO.getCity(), this::trueSpecification))
                .and(fieldEqualsOrDefault("roleName", vacancyQueryParamDTO.getRole(), this::trueSpecification))
                .and(fieldEqualsOrDefault("isActive", vacancyQueryParamDTO.getStatus(), this::trueSpecification))
                .and(fieldInOrDefault("grade", vacancyQueryParamDTO.getGrade(), this::trueSpecification))
                .and(fieldGreaterThanOrNull("salary", vacancyQueryParamDTO.getSalaryFrom()))
                .and(fieldLessThanOrNull("salary", vacancyQueryParamDTO.getSalaryTo()))
                .and(withMine(vacancyQueryParamDTO.getMine(), vacancyQueryParamDTO.getAuthor()))
                .and(withFav(vacancyQueryParamDTO.getFavs(), vacancyQueryParamDTO.getAuthor()))
                ;
    }

    private Specification<Vacancy> withMine(Boolean mine, String ownerId){
            if (mine == null || ownerId == null || ownerId.isEmpty()){
                return trueSpecification();
            }
            if(mine){
                return fieldEquals("employee.id", UUID.fromString(ownerId));
            }
            return fieldNotEquals("employee.id", UUID.fromString(ownerId));
    }

    private Specification<Vacancy> withFav(Boolean favs, String ownerId){

            if (favs == null || !favs || ownerId == null || ownerId.isEmpty()){
                return trueSpecification();
            }
            return fieldEquals("favoriteVacancies.employee.id", UUID.fromString(ownerId));

    }
}
