package com.projet_demarche.demarche_facile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Bureau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBureau;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String ville;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @ManyToOne
    @JoinColumn(name="idAdmin")
    private Admin admin;

    @OneToMany
    @JoinTable(
            name = "bureau_document",
            joinColumns = @JoinColumn(name = "idBureau"),
            inverseJoinColumns = @JoinColumn(name = "idDocument")
    )
    @JsonIgnore
    private List<Document> documents;
}
