package com.example.VacanciesAndResumes.specifications.Interface.primitive;

import com.example.VacanciesAndResumes.specifications.Interface.common.BaseFilterInterface;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import io.vavr.control.Option;

import static java.util.Objects.isNull;

/**
 * Построение спецификаций, основанных на работе со списком значений.
 *
 * @param <T> тип сущности
 */
public interface PrimitiveCollectionFilterInterface <T> extends BaseFilterInterface<T> {

    /**
     * Присутствие в списке или спецификация по умолчанию если передаваемое значение пустое.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param values список значений фильтрации
     * @param defaultCall спецификация по умолчанию
     * @return сформированная спецификация
     */
    default <R> Specification<T> fieldInOrDefault(final String field, final Collection<R> values, Supplier<Specification<T>> defaultCall) {
        if (isNull(values)) {
            return defaultCall.get();
        }

        if (values.isEmpty()) {
            return falseSpecification();
        }

        return orSpecification(values, valuesPart -> (r, cq, cb) -> getPathToField(r, field).in(valuesPart));
    }

    /**
     * Присутствие в списке.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param values список значений фильтрации
     * @return сформированная спецификация
     */
    default <R> Specification<T> fieldIn(final String field, final Collection<R> values) {
        return fieldInOrDefault(field, values, this::trueSpecification);
    }

    /**
     * Отсутствие значения в списке или спецификация по умолчанию если передаваемое значение пустое.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param values список значений фильтрации
     * @param defaultCall спецификация по умолчанию
     * @return сформированная спецификация
     */
    default <R> Specification<T> fieldNotInOrDefault(final String field, final Collection<R> values, Supplier<Specification<T>> defaultCall) {
        if (isNull(values)) {
            return defaultCall.get();
        }

        if (values.isEmpty()) {
            return trueSpecification();
        }

        return andSpecification(values, valuesPart -> (r, cq, cb) -> getPathToField(r, field).in(valuesPart).not());
    }

    /**
     * Отсутствие значения в списке или спецификация по умолчанию если передаваемое значение пустое.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param values список значений фильтрации
     * @return сформированная спецификация
     */
    default <R> Specification<T> fieldNotIn(final String field, final Collection<R> values) {
        return fieldNotInOrDefault(field, values, this::trueSpecification);
    }
}
