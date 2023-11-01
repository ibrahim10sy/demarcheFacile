package com.projet_demarche.demarche_facile.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDocument;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String audio;

    @Column(nullable = true)
    private String fichier;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="idGuide")
    private Guide guide;

    @ManyToMany
    @JoinColumn(name = "idBureau")
    private List<Bureau> bureaux;
}
