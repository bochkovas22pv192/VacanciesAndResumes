package com.example.VacanciesAndResumes.specifications.Interface.primitive;

import com.example.VacanciesAndResumes.specifications.Interface.common.BaseFilterInterface;
import org.springframework.data.jpa.domain.Specification;

import static java.util.Optional.ofNullable;
import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Построение спецификаций, основанных на операциях сравнения.
 *
 * @param <T> тип сущности
 */
public interface PrimitiveRangeFilterInterface<T> extends BaseFilterInterface<T> {
    /**
     * Больше для Number.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param gtValue значение
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldGt(final String field, final R gtValue) {
        return ofNullable(gtValue)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.gt(getPathToField(r, field), v))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Больше для любого типа.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param gtValue значение
     * @return сформированная спецификация
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    default <R extends Comparable> Specification<T> fieldGreaterThan(final String field, final R gtValue) {
        return ofNullable(gtValue)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.greaterThan(getPathToField(r, field), v))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Больше или Null.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param gtValue значение
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldGtOrNull(final String field, final R gtValue) {
        return ofNullable(gtValue)
                .map(v -> fieldGt(field, v).or(fieldIsNull(field)))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Больше или Null.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param gtValue значение
     * @return сформированная спецификация
     */
    @SuppressWarnings("rawtypes")
    default <R extends Comparable> Specification<T> fieldGreaterThanOrNull(final String field, final R gtValue) {
        return ofNullable(gtValue)
                .map(v -> fieldGreaterThan(field, v).or(fieldIsNull(field)))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Больше или равно.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param geValue значение
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldGe(final String field, final R geValue) {
        return ofNullable(geValue)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.ge(getPathToField(r, field), v))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Больше или равно.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param geValue значение
     * @return сформированная спецификация
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    default <R extends Comparable> Specification<T> fieldGreaterThanOrEqual(final String field, final R geValue) {
        return ofNullable(geValue)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.greaterThanOrEqualTo(getPathToField(r, field), v))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Больше, равно или null.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param geValue значение
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldGeOrNull(final String field, final R geValue) {
        return ofNullable(geValue)
                .map(v -> fieldGe(field, v).or(fieldIsNull(field)))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Больше, равно или null.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param geValue значение
     * @return сформированная спецификация
     */
    @SuppressWarnings("rawtypes")
    default <R extends Comparable> Specification<T> fieldGreaterThanOrEqualOrNull(final String field, final R geValue) {
        return ofNullable(geValue)
                .map(v -> fieldGreaterThanOrEqual(field, v).or(fieldIsNull(field)))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Меньше.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param ltValue значение
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldLt(final String field, final R ltValue) {
        return ofNullable(ltValue)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.lt(getPathToField(r, field), v))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Меньше.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param ltValue значение
     * @return сформированная спецификация
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    default <R extends Comparable> Specification<T> fieldLessThan(final String field, final R ltValue) {
        return ofNullable(ltValue)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.lessThan(getPathToField(r, field), v))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Меньше или NULL.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param ltValue значение
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldLtOrNull(final String field, final R ltValue) {
        return ofNullable(ltValue)
                .map(v -> fieldLt(field, v).or(fieldIsNull(field)))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Меньше или NULL.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param ltValue значение
     * @return сформированная спецификация
     */
    @SuppressWarnings("rawtypes")
    default <R extends Comparable> Specification<T> fieldLessThanOrNull(final String field, final R ltValue) {
        return ofNullable(ltValue)
                .map(v -> fieldLessThan(field, v).or(fieldIsNull(field)))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Меньше или равенство.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param leValue значение
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldLe(final String field, final R leValue) {
        return ofNullable(leValue)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.le(getPathToField(r, field), v))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Меньше или равенство.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param leValue значение
     * @return сформированная спецификация
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    default <R extends Comparable> Specification<T> fieldLessThanOrEqual(final String field, final R leValue) {
        return ofNullable(leValue)
                .map(v -> (Specification<T>) (r, cq, cb) -> cb.lessThanOrEqualTo(getPathToField(r, field), v))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Меньше, равенство или Null.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param leValue значение
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldLeOrNull(final String field, final R leValue) {
        return ofNullable(leValue)
                .map(v -> fieldLe(field, v).or(fieldIsNull(field)))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Меньше, равенство или NULL.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param leValue значение
     * @return сформированная спецификация
     */
    @SuppressWarnings("rawtypes")
    default <R extends Comparable> Specification<T> fieldLessThanOrEqualOrNull(final String field, final R leValue) {
        return ofNullable(leValue)
                .map(v -> fieldLessThanOrEqual(field, v).or(fieldIsNull(field)))
                .orElseGet(this::trueSpecification);
    }

    /**
     * Попадание в отрезок, включая правую и левую границы.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param geValue левая граница (включительно)
     * @param leValue правая граница (включительно)
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldBetweenInclusionNumber(final String field, final R geValue, final R leValue) {
        return where(fieldGe(field, geValue)).and(fieldLe(field, leValue));
    }

    /**
     * Попадание в интервал, исключая правую и левую границы.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param gtValue левая граница (не учитывается)
     * @param ltValue правая граница (не учитывается)
     * @return сформированная спецификация
     */
    default <R extends Number> Specification<T> fieldBetweenExclusionNumber(final String field, final R gtValue, final R ltValue) {
        return where(fieldGt(field, gtValue)).and(fieldLt(field, ltValue));
    }

    /**
     * Попадание в отрезок, включая правую и левую границы.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param geValue левая граница (включительно)
     * @param leValue правая граница (включительно)
     * @return сформированная спецификация
     */
    @SuppressWarnings("rawtypes")
    default <R extends Comparable> Specification<T> fieldBetweenInclusionComparable(final String field, final R geValue, final R leValue) {
        return where(fieldGreaterThanOrEqual(field, geValue)).and(fieldLessThanOrEqual(field, leValue));
    }

    /**
     * Попадание в интервал, исключая правую и левую границы.
     *
     * @param <R> тип передаваемого значения
     * @param field имя поля
     * @param gtValue левая граница (не учитывается)
     * @param ltValue правая граница (не учитывается)
     * @return сформированная спецификация
     */
    @SuppressWarnings("rawtypes")
    default <R extends Comparable> Specification<T> fieldBetweenExclusionComparable(final String field, final R gtValue, final R ltValue) {
        return where(fieldGreaterThan(field, gtValue)).and(fieldLessThan(field, ltValue));
    }
}
