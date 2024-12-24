package com.example.VacanciesAndResumes.specifications;

import com.example.VacanciesAndResumes.models.Customer;
import com.example.VacanciesAndResumes.models.Employee;
import com.example.VacanciesAndResumes.models.Vacancy;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SpecificationVacancySearch {

    public Specification<Vacancy> buildSpecification (String q){
        if (q == null){
            return null;
        }
        return withTitle(q)
                .or(withRole(q))
                .or(withCustomerName(q));
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
}
