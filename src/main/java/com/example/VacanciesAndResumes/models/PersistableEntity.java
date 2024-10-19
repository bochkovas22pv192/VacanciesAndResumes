package com.example.VacanciesAndResumes.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class PersistableEntity implements Persistable<UUID> {
    @Id
    @GeneratedValue
    private UUID id;

    @Override
    public  boolean isNew(){return  false;}
}
