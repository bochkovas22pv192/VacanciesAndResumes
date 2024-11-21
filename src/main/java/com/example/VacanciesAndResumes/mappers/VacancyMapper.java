package com.example.VacanciesAndResumes.mappers;

import com.example.VacanciesAndResumes.DTOs.CommentVacancyDTO;
import com.example.VacanciesAndResumes.DTOs.CommentVacancyPostDTO;
import com.example.VacanciesAndResumes.DTOs.CustomerDTO;
import com.example.VacanciesAndResumes.DTOs.VacancyDTO;
import com.example.VacanciesAndResumes.DTOs.patch.CommentVacancyPatchDTO;
import com.example.VacanciesAndResumes.DTOs.patch.VacancyPatchDTO;
import com.example.VacanciesAndResumes.models.CommentVacancy;
import com.example.VacanciesAndResumes.models.Customer;
import com.example.VacanciesAndResumes.models.Employee;
import com.example.VacanciesAndResumes.models.Vacancy;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    VacancyPatchDTO vacancyToVacancyPatchDTO(Vacancy entity);
    Vacancy vacancyPatchDTOToVacancy(VacancyPatchDTO  entity);

    @Mapping(target = "author", source = "entity.employee")
    @Mapping(target = "timestamp", source = "entity.createdAt", dateFormat = "yyyy-MM-dd-HH-mm-ss")
    CommentVacancyDTO commentVacancyToCommentVacancyDTO(CommentVacancy entity);
    CommentVacancy commentVacancyPostDTOToCommentVacancy(CommentVacancyPostDTO entity);

    List<CommentVacancyDTO> commentVacancyToCommentVacancyDTO(List<CommentVacancy> entity);

    CommentVacancyPatchDTO commentVacancyToCommentVacancyPatchDTO (CommentVacancy entity);

    default String employeeToAuthor(Employee entity){
        if (entity!=null){
            String result = "";
            result += entity.getFirstName() + " " + entity.getLastName();
            return result;
        }
        return null;
    }
}
