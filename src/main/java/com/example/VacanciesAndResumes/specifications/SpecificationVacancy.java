package com.example.VacanciesAndResumes.specifications;

import com.example.VacanciesAndResumes.DTOs.VacancyQueryParamDTO;
import com.example.VacanciesAndResumes.models.Employee;
import com.example.VacanciesAndResumes.models.FavoriteVacancy;
import com.example.VacanciesAndResumes.models.Vacancy;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SpecificationVacancy {

    public Specification<Vacancy> build (VacancyQueryParamDTO vacancyQueryParamDTO){
        return withCountry(vacancyQueryParamDTO.getCountry())
                .and(withRegion(vacancyQueryParamDTO.getRegion()))
                .and(withCity(vacancyQueryParamDTO.getCity()))
                .and(withRole(vacancyQueryParamDTO.getRole()))
                .and(withStatus(vacancyQueryParamDTO.getStatus()))
                .and(withGrade(vacancyQueryParamDTO.getGrade()))
                .and(withSalaryFrom(vacancyQueryParamDTO.getSalaryFrom()))
                .and(withSalaryTo(vacancyQueryParamDTO.getSalaryTo()))
                .and(withMine(vacancyQueryParamDTO.getMine(), vacancyQueryParamDTO.getAuthor()))
                .and(withFav(vacancyQueryParamDTO.getFavs(), vacancyQueryParamDTO.getAuthor()))
                ;
    }

    private Specification<Vacancy> withCountry(String country){

        return (root, query, criteriaBuilder) -> {
            if (country == null || country.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("country"), country);
        };
    }

    private Specification<Vacancy> withRegion(String region){

        return (root, query, criteriaBuilder) -> {
            if (region == null || region.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("region"), region);
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

    private Specification<Vacancy> withRole(String role){

        return (root, query, criteriaBuilder) -> {
            if (role == null || role.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("roleName"), role);
        };
    }

    private Specification<Vacancy> withStatus(Boolean status){

        return (root, query, criteriaBuilder) -> {
            if (status == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("isActive"), status);
        };
    }

    private Specification<Vacancy> withGrade(List<String> grades){

        return (root, query, criteriaBuilder) -> {
            if (grades == null){
                return criteriaBuilder.conjunction();
            }
            Predicate result = criteriaBuilder.equal(root.get("grade"), grades.getFirst());
            for (int i = 1; i < grades.size(); i++) {
                result = criteriaBuilder.or(result, criteriaBuilder.equal(root.get("grade"), grades.get(i)));
            }
            return result;
        };
    }

    private Specification<Vacancy> withSalaryFrom(Integer salaryFrom){

        return (root, query, criteriaBuilder) -> {
            if (salaryFrom == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThan(root.get("salary"), salaryFrom);
        };
    }

    private Specification<Vacancy> withSalaryTo(Integer salaryTo){

        return (root, query, criteriaBuilder) -> {
            if (salaryTo == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThan(root.get("salary"), salaryTo);
        };
    }

    private Specification<Vacancy> withMine(Boolean mine, String ownerId){

        return (root, query, criteriaBuilder) -> {
            if (mine == null || ownerId == null || ownerId.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            Join<Vacancy, Employee> vacancyOwner = root.join("employee");
            if(mine){
                return criteriaBuilder.equal(vacancyOwner.get("id"), UUID.fromString(ownerId));
            }
            return criteriaBuilder.notEqual(vacancyOwner.get("id"), UUID.fromString(ownerId));
        };
    }

    private Specification<Vacancy> withFav(Boolean favs, String ownerId){

        return (root, query, criteriaBuilder) -> {
            if (favs == null || !favs || ownerId == null || ownerId.isEmpty()){
                return criteriaBuilder.conjunction();
            }
            Join<Join<Vacancy, FavoriteVacancy>, Employee> vacancyFavs = root.join("favoriteVacancies").join("employee");
            return criteriaBuilder.equal(vacancyFavs.get("id"), UUID.fromString(ownerId));
        };
    }
}
