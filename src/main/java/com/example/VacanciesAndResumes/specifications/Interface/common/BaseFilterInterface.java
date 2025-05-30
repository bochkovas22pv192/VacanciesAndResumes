package com.example.VacanciesAndResumes.specifications.Interface.common;

import com.google.common.collect.Lists;
import io.vavr.control.Option;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static java.lang.String.join;

/**
 * Содержит базовые методы по работе со спецификацией.
 *
 * @param <T> тип сущности
 */
public interface BaseFilterInterface<T> {

    public static final int MAX_QUERY_PARAMS = 1000;

    /**
     * Спецификация, которая пропускает все записи (1=1).
     *
     * @return спецификация
     */
    default Specification<T> trueSpecification() {
        return (r, cq, cb) -> cb.conjunction();
    }

    /**
     * Спецификация, которая не пропускает все записи (1=0).
     *
     * @return спецификация
     */
    default Specification<T> falseSpecification() {
        return (r, cq, cb) -> cb.or();
    }

    /**
     * Сформировать путь до поля в сущности.
     *
     * @param <E> тип поля
     * @param root головная сущность
     * @param fieldName наименование поля
     * @return сформированный путь
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    default <E> Path<E> getPathToField(final Root root, final String fieldName) {
        String[] fields = fieldName.split("\\.");

        if (fields.length > 1) {
            Path path = root.get(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                path = path.get(fields[i]);
            }
            return path;
        }

        return root.get(fieldName);
    }

    /**
     * Сформировать путь для вложенной сущности.
     *
     * @param fields список вложенных друг в друга сущностей. Порядок важен (от первого уровне вложенности).
     * @return сформированный итоговый путь
     */
    default String getInnerEntityField(final String... fields) {
        return join(".", fields);
    }

    /**
     * Проверка, что поле пустое.
     *
     * @param field имя поля
     * @return спецификация
     */
    default Specification<T> fieldIsNull(final String field) {
        return (r, cq, cb) -> cb.isNull(getPathToField(r, field));
    }

    /**
     * Проверка, что поле не пустое.
     *
     * @param field имя поля
     * @return спецификация
     */
    default Specification<T> fieldIsNotNull(final String field) {
        return (r, cq, cb) -> cb.isNotNull(getPathToField(r, field));
    }

    /**
     * Проверка, что поле {@link Boolean#TRUE}.
     *
     * @param field имя поля
     * @return спецификация
     */
    default Specification<T> fieldIsTrue(final String field) {
        return (r, cq, cb) -> cb.isTrue(getPathToField(r, field));
    }

    /**
     * Проверка, что поле {@link Boolean#FALSE}.
     *
     * @param field имя поля
     * @return спецификация
     */
    default Specification<T> fieldIsFalse(final String field) {
        return (r, cq, cb) -> cb.isFalse(getPathToField(r, field));
    }

    /**
     * Разбиение списка элементов на списки длинной не более {@link #MAX_QUERY_PARAMS}.
     *
     * @param items список элементов
     * @param <C> тип элемента
     * @return части общего списка
     */
    default <C> Option<List<List<C>>> partition(Collection<C> items) {
        return Option.of(items)
                .filter(it -> it.size() > MAX_QUERY_PARAMS)
                .map(List::copyOf)
                .map(it -> Lists.partition(it, MAX_QUERY_PARAMS));
    }

    /**
     * Построение спецификации по списку элементов с логикой "или".
     *
     * @param items список элементов
     * @param specMapper мапер спецификаций
     * @param <C> тип элемента коллекции
     * @return спецификация с логикой "или"
     */
    default <C> Specification<T> orSpecification(Collection<C> items, Function<Collection<C>, Specification<T>> specMapper) {
        return partition(items)
                .map(it -> it.stream().map(specMapper).reduce(Specification::or))
                .flatMap(Option::ofOptional)
                .getOrElse(() -> specMapper.apply(items));
    }

    /**
     * Построение спецификации по списку элементов с логикой "и".
     *
     * @param items список элементов
     * @param specMapper мапер спецификаций
     * @param <C> тип элемента коллекции
     * @return спецификация с логикой "или"
     */
    default <C> Specification<T> andSpecification(Collection<C> items, Function<Collection<C>, Specification<T>> specMapper) {
        return partition(items)
                .map(it -> it.stream().map(specMapper).reduce(Specification::and))
                .flatMap(Option::ofOptional)
                .getOrElse(() -> specMapper.apply(items));
    }

}


