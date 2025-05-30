package com.example.VacanciesAndResumes.specifications;

import com.example.VacanciesAndResumes.models.CommentVacancy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.reliab.tech.core.specification.helper.PrimitiveFilterHelper;

import java.util.UUID;

@Component
public class BaseFilterCommentVacancy implements PrimitiveFilterHelper<CommentVacancy> {

    public Specification<CommentVacancy> byVacancyId(UUID vacancyId) {
        return fieldEqualsOrDefault("vacancy.id", vacancyId, this::falseSpecification);
    }
}
