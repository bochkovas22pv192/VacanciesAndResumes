package com.example.VacanciesAndResumes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_documents")
public class Document extends PersistableEntity {

    @ManyToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Lob
    @Column
    private byte[] document ;
}
