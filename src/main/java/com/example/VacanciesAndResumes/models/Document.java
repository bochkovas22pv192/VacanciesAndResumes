package com.example.VacanciesAndResumes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_documents")
public class Document extends PersistableEntity {

    @JsonBackReference
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="candidate_id")
    private Candidate candidate;

    @Lob
    @Column
    private byte[] document ;
}
