package com.example.VacanciesAndResumes.specifications.Interface;

import com.example.VacanciesAndResumes.specifications.Interface.primitive.PrimitiveCollectionFilterInterface;
import com.example.VacanciesAndResumes.specifications.Interface.primitive.PrimitiveRangeFilterInterface;
import com.example.VacanciesAndResumes.specifications.Interface.primitive.PrimitiveSingleFilterInterface;

public interface PrimitiveFilterInterface<T>
        extends PrimitiveSingleFilterInterface<T>, PrimitiveCollectionFilterInterface<T>, PrimitiveRangeFilterInterface<T> {
}
