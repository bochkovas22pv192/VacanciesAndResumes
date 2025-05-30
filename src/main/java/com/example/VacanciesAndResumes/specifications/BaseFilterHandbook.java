package com.example.VacanciesAndResumes.specifications;

import com.example.VacanciesAndResumes.models.Handbook;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.reliab.tech.core.specification.helper.PrimitiveFilterHelper;

@Component
public class BaseFilterHandbook implements PrimitiveFilterHelper<Handbook> {

    public Specification<Handbook> byCode(String code) {
        return fieldEqualsOrDefault("code", code, this::falseSpecification);
    }
}
