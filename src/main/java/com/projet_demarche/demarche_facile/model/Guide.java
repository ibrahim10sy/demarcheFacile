package com.projet_demarche.demarche_facile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGuide;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String audio;

    @Column(nullable = false)
    private String libelle;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Admin admin;

    @OneToMany(mappedBy = "guide")
    @JsonIgnore
    private List<Document> documents;
}
