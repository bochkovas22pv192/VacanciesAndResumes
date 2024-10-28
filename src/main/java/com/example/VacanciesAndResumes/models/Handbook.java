package com.example.VacanciesAndResumes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
public class Handbook extends PersistableEntity  {

    @Column(nullable = false, length=32)
    private String code;

    @Column(nullable = false, length=64)
    private String keyName;

    @Column(nullable = false, length=64)
    private String valueName;
}
