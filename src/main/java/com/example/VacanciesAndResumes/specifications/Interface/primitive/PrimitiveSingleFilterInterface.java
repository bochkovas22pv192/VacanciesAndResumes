package com.example.VacanciesAndResumes.specifications.Interface.primitive;

import com.example.VacanciesAndResumes.specifications.Interface.common.BaseFilterInterface;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Supplier;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Построение спецификаций, основанных на работе с одиночным значением.
 *
 * @param <T> тип сущности
 */
public interface PrimitiveSingleFilterInterface<T> extends BaseFilterInterface<T> {
    /**
     * Полное совпадение или спецификация по умолчанию если передаваемое значение пустое.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param value значение фильтра
     * @param defaultCall спецификация по умолчанию
     * @return сформированная спецификация
     */
    default <R> Specification<T> fieldEqualsOrDefault(final String field, final R value, Supplier<Specification<T>> defaultCall) {
        return ofNullable(value)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.equal(getPathToField(r, field), v))
                .orElseGet(defaultCall);
    }

    /**
     * Полное совпадение.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param value значение фильтра
     * @return сформированная спецификация
     */
    default <R> Specification<T> fieldEquals(final String field, final R value) {
        return fieldEqualsOrDefault(field, value, this::trueSpecification);
    }

    /**
     * Полное исключение или спецификация по умолчанию если передаваемое значение пустое.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param value значение фильтра
     * @param defaultCall спецификация по умолчанию
     * @return сформированная спецификация
     */
    default <R> Specification<T> fieldNotEqualsOrDefault(final String field, final R value, Supplier<Specification<T>> defaultCall) {
        return ofNullable(value)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.notEqual(getPathToField(r, field), v))
                .orElseGet(defaultCall);
    }

    /**
     * Полное исключение.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param value значение фильтра
     * @return сформированная спецификация
     */
    default <R> Specification<T> fieldNotEquals(final String field, final R value) {
        return fieldNotEqualsOrDefault(field, value, this::trueSpecification);
    }

    /**
     * Частичное строковое совпадение или спецификация по умолчанию если передаваемое значение пустое.
     *
     * @param field имя поля
     * @param value значение фильтра
     * @param defaultCall спецификация по умолчанию
     * @return сформированная спецификация
     */
    default Specification<T> fieldContainsOrDefault(final String field, final String value, Supplier<Specification<T>> defaultCall) {
        if (isNotBlank(value)) {
            return (r, cq, cb) -> cb.like(cb.upper(getPathToField(r, field)), String.format("%%%s%%", value.toUpperCase()));
        }

        return defaultCall.get();
    }

    /**
     * Частичное строковое совпадение.
     *
     * @param field имя поля
     * @param value значение фильтра
     * @return сформированная спецификация
     */
    default Specification<T> fieldContains(final String field, final String value) {
        return fieldContainsOrDefault(field, value, this::trueSpecification);
    }

    /**
     * Частичное строковое исключение или спецификация по умолчанию если передаваемое значение пустое.
     *
     * @param field имя поля
     * @param value значение фильтра
     * @param defaultCall спецификация по умолчанию
     * @return сформированная спецификация
     */
    default Specification<T> fieldNotContainsOrDefault(final String field, final String value, Supplier<Specification<T>> defaultCall) {
        if (isNotBlank(value)) {
            return (r, cq, cb) -> cb.notLike(cb.upper(getPathToField(r, field)), String.format("%%%s%%", value.toUpperCase()));
        }

        return defaultCall.get();
    }

    /**
     * Частичное строковое исключение.
     *
     * @param field имя поля
     * @param value значение фильтра
     * @return сформированная спецификация
     */
    default Specification<T> fieldNotContains(final String field, final String value) {
        return fieldNotContainsOrDefault(field, value, this::trueSpecification);
    }

    /**
     * Совпадение с началом строки.
     *
     * @param field имя поля
     * @param value значение фильтра
     * @param defaultCall спецификация по умолчанию
     * @return сформированная спецификация
     */
    default Specification<T> fieldStartsWithOrDefault(final String field, final String value, Supplier<Specification<T>> defaultCall) {
        if (isNotBlank(value)) {
            return (r, cq, cb) -> cb.like(cb.upper(getPathToField(r, field)), String.format("%s%%", value.toUpperCase()));
        }

        return defaultCall.get();
    }

    /**
     * Совпадение с началом строки.
     *
     * @param field имя поля
     * @param value значение фильтра
     * @return сформированная спецификация
     */
    default Specification<T> fieldStartWith(final String field, final String value) {
        return fieldStartsWithOrDefault(field, value, this::trueSpecification);
    }

    /**
     * Совпадение с концом строки.
     *
     * @param field имя поля
     * @param value значение фильтра
     * @param defaultCall спецификация по умолчанию
     * @return сформированная спецификация
     */
    default Specification<T> fieldEndsWithOrDefault(final String field, final String value, Supplier<Specification<T>> defaultCall) {
        if (isNotBlank(value)) {
            return (r, cq, cb) -> cb.like(cb.upper(getPathToField(r, field)), String.format("%%%s", value.toUpperCase()));
        }

        return defaultCall.get();
    }

    /**
     * Совпадение с концом строки.
     *
     * @param field имя поля
     * @param value значение фильтра
     * @return сформированная спецификация
     */
    default Specification<T> fieldEndsWith(final String field, final String value) {
        return fieldEndsWithOrDefault(field, value, this::trueSpecification);
    }
}
