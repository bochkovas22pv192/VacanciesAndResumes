package com.example.VacanciesAndResumes.mappers;

import com.example.VacanciesAndResumes.DTOs.*;
import com.example.VacanciesAndResumes.DTOs.patch.CommentVacancyPatchDTO;
import com.example.VacanciesAndResumes.DTOs.patch.VacancyPatchDTO;
import com.example.VacanciesAndResumes.models.*;
import org.hibernate.Hibernate;
import org.mapstruct.*;

import java.util.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface VacancyMapper {
    @Mapping(target = "ownerId", source = "entity.employee.id")
    VacancyDTO vacancyToVacancyDTO(Vacancy entity);
    Vacancy vacancyDTOToVacancy(VacancyDTO entity);

    List<VacancyDTO> vacancyToVacancyDTO(List<Vacancy> entity);

    @Mapping(target = "status", source = "active")
    @Mapping(target = "customerName", source = "entity.customer", qualifiedByName = "getCustomerName")
    @Mapping(target = "inFav", source = "entity", qualifiedByName="isInFav")
    @Mapping(target = "candidateCount", source = "entity.vacancyCandidates", qualifiedByName="countCandidates")
    VacancyGetDTO vacancyToVacancyGetDTO(Vacancy entity, @Context UUID employeeId);
    List<VacancyGetDTO> vacancyToVacancyGetDTO(List<Vacancy> entity, @Context UUID employeeId);

    CustomerDTO customerToCustomerDTO(Customer entity);
    Customer customerDTOToCustomer(CustomerDTO entity);

    VacancyPatchDTO vacancyToVacancyPatchDTO(Vacancy entity);
    Vacancy vacancyPatchDTOToVacancy(VacancyPatchDTO  entity);

    @Mapping(target = "author", source = "entity.employee")
    @Mapping(target = "timestamp", source = "entity.createdAt", dateFormat = "yyyy-MM-dd-HH-mm-ss")
    CommentVacancyDTO commentVacancyToCommentVacancyDTO(CommentVacancy entity);
    CommentVacancy commentVacancyPostDTOToCommentVacancy(CommentVacancyPostDTO entity);

    List<CommentVacancyDTO> commentVacancyToCommentVacancyDTO(List<CommentVacancy> entity);

    CommentVacancyPatchDTO commentVacancyToCommentVacancyPatchDTO (CommentVacancy entity);


    @Mapping(target = "salaryFrom", source = "salary_from")
    @Mapping(target = "salaryTo", source = "salary_to")
    @Mapping(target = "author", source = "owner_id")
    VacancyQueryParamDTO queryMapToVacancyQueryParamDTO (Map<String, String> entity);

    default List<String> stringSplitToList(String entity){
        if (entity == null || entity.isEmpty() || entity.isBlank()){
            return null;
        }
        return Arrays.asList(entity.split("\\s+"));
    }

    default Integer integerFromString(String entity){
        return entity == null || entity.isEmpty() ? null : Integer.parseInt(entity);
    }

    default Boolean booleanFromString(String entity){
        return entity == null || entity.isEmpty() ? null : Boolean.parseBoolean(entity);
    }

    default String employeeToAuthor(Employee entity){
        if (entity!=null){
            String result = "";
            result += entity.getFirstName() + " " + entity.getLastName();
            return result;
        }
        return null;
    }


    @Named("getCustomerName")
    default String getCustomerName(Customer entity){
        return entity == null ? null : entity.getName();
    }

    @Named("isInFav")
    default Boolean isInFav(Vacancy entity, @Context UUID employeeId){
        if (entity == null || employeeId == null){
            return null;
        }

        return entity.getFavoriteVacancies().stream().anyMatch(e -> {
            assert e.getId() != null;
            return e.getVacancy().getId().equals(entity.getId()) && e.getEmployee().getId().equals(employeeId);
        });
    }

    @Named("countCandidates")
    default Integer countCandidates(List<Candidate> entity){
        return entity == null ? null :  entity.size();
    }
}
