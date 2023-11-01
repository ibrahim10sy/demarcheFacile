package com.projet_demarche.demarche_facile.model;

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

    @ManyToMany
    @JoinColumn(name = "idDocument")
    private List<Document> documents;
}
