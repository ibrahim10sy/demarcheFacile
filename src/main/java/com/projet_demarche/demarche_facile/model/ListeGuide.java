package com.projet_demarche.demarche_facile.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ListeGuide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idListe;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String audio;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String service;
}
